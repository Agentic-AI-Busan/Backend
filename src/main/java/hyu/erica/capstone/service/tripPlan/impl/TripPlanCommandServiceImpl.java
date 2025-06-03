package hyu.erica.capstone.service.tripPlan.impl;

import static java.time.temporal.ChronoUnit.DAYS;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.domain.Attraction;
import hyu.erica.capstone.domain.Restaurant;
import hyu.erica.capstone.domain.TripPlan;
import hyu.erica.capstone.domain.TripScheduleItem;
import hyu.erica.capstone.domain.User;
import hyu.erica.capstone.domain.enums.PlaceType;
import hyu.erica.capstone.domain.mapping.PreferAttraction;
import hyu.erica.capstone.domain.mapping.PreferRestaurant;
import hyu.erica.capstone.repository.AttractionRepository;
import hyu.erica.capstone.repository.PreferAttractionRepository;
import hyu.erica.capstone.repository.PreferRestaurantRepository;
import hyu.erica.capstone.repository.RestaurantRepository;
import hyu.erica.capstone.repository.TripPlanRepository;
import hyu.erica.capstone.repository.TripScheduleItemRepository;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.service.tripPlan.TripPlanCommandService;
import hyu.erica.capstone.web.dto.trip.request.SaveAttractionRequestDTO;
import hyu.erica.capstone.web.dto.trip.request.SaveRestaurantRequestDTO;
import hyu.erica.capstone.web.dto.tripPlan.request.UpdateAllScheduleOrderRequest;
import hyu.erica.capstone.web.dto.tripPlan.request.UpdateAllScheduleOrderRequest.ScheduleOrderItemDTO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import hyu.erica.capstone.web.dto.tripPlan.request.UpdateMemoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TripPlanCommandServiceImpl implements TripPlanCommandService {

    private final UserRepository userRepository;
    private final AttractionRepository attractionRepository;
    private final RestaurantRepository restaurantRepository;

    private final PreferAttractionRepository preferAttractionRepository;
    private final PreferRestaurantRepository preferRestaurantRepository;
    private final TripScheduleItemRepository tripScheduleItemRepository;
    private final TripPlanRepository tripPlanRepository;

    @Override
    public Long confirmAttractionRecommendation(Long tripPlanId, Long userId, SaveAttractionRequestDTO request) {
        if (!tripPlanRepository.existsById(tripPlanId)) {
            throw new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND);
        }

        List<PreferAttraction> preferAttractions = preferAttractionRepository.findAllByTripPlanId(tripPlanId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        for (PreferAttraction preferAttraction : preferAttractions) {
            if (!request.attractionIds().contains(preferAttraction.getAttraction().getContentId())) {
                preferAttraction.setPrefer(false);
            } else {
                preferAttraction.setPrefer(true);
            }
        }

        // 입력 받은 것 중, 기존 DB에 없는 것들은 새로 추가.
        for (Long attractionId : request.attractionIds()) {
            if (!preferAttractionRepository.existsByTripPlanIdAndAttractionContentId(tripPlanId, attractionId)) {
                PreferAttraction preferAttraction = PreferAttraction.builder()
                        .tripPlan(tripPlanRepository.getReferenceById(tripPlanId))
                        .attraction(attractionRepository.getReferenceById(attractionId))
                        .user(user)
                        .isPrefer(true)
                        .build();
                preferAttractionRepository.save(preferAttraction);
            }
        }

        preferAttractionRepository.flush();

        return tripPlanId;
    }

    @Override
    public Long confirmRestaurantRecommendation(Long tripPlanId, Long userId, SaveRestaurantRequestDTO request) {
        if (!tripPlanRepository.existsById(tripPlanId)) {
            throw new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND);
        }

        List<PreferRestaurant> preferRestaurants = preferRestaurantRepository.findAllByTripPlanId(tripPlanId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        for (PreferRestaurant preferRestaurant : preferRestaurants) {
            if (!request.restaurantIds().contains(preferRestaurant.getRestaurant().getId())) {
                preferRestaurant.setPrefer(false);
            } else {
                preferRestaurant.setPrefer(true);
            }
        }

        // 입력 받은 것 중, 기존 DB에 없는 것들은 새로 추가.
        for (Long restaurantId : request.restaurantIds()) {
            if (!preferRestaurantRepository.existsByTripPlanIdAndRestaurantId(tripPlanId, restaurantId)) {
                PreferRestaurant preferRestaurant = PreferRestaurant.builder()
                        .tripPlan(tripPlanRepository.getReferenceById(tripPlanId))
                        .restaurant(restaurantRepository.getReferenceById(restaurantId))
                        .user(user)
                        .isPrefer(true)
                        .build();
                preferRestaurantRepository.save(preferRestaurant);
            }
        }
        preferRestaurantRepository.flush();

        createTripPlanFinal(tripPlanId);
        return tripPlanId;
    }

    @Override
    public void updatePlan(UpdateAllScheduleOrderRequest request) {
        List<TripScheduleItem> existingItems = tripScheduleItemRepository
                .findAllByTripPlanId(request.tripPlanId());

        Map<Long, TripScheduleItem> existingItemMap = existingItems.stream()
                .collect(Collectors.toMap(TripScheduleItem::getId, item -> item));

        Set<Long> updatedIds = new HashSet<>();

        for (ScheduleOrderItemDTO dto : request.items()) {
            if (dto.id() != null && existingItemMap.containsKey(dto.id())) {
                // 수정
                TripScheduleItem item = existingItemMap.get(dto.id());
                item.updateDayAndOrder(dto.dayNumber(), dto.orderInDay());
                updatedIds.add(dto.id());
            } else {

                TripScheduleItem newItem = TripScheduleItem.builder()
                        .tripPlan(tripPlanRepository.getReferenceById(request.tripPlanId()))
                        .dayNumber(dto.dayNumber())
                        .orderInDay(dto.orderInDay())
                        .placeType(dto.placeType())
                        .attraction(dto.attractionId() != null ? attractionRepository.getReferenceById(dto.attractionId()) : null)
                        .restaurant(dto.restaurantId() != null ? restaurantRepository.getReferenceById(dto.restaurantId()) : null)
                        .build();
                tripScheduleItemRepository.save(newItem);
            }
        }

        // 삭제 대상 처리 (요청에 없는 기존 ID는 삭제)
        for (TripScheduleItem item : existingItems) {
            if (!updatedIds.contains(item.getId())) {
                tripScheduleItemRepository.delete(item);
            }
        }
    }

    @Override
    public void editAttractionRecommendation(Long tripPlansId, SaveAttractionRequestDTO request) {
        if (!tripPlanRepository.existsById(tripPlansId)) {
            throw new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND);
        }

        List<PreferAttraction> preferAttractions = preferAttractionRepository.findAllByTripPlanId(tripPlansId);

        for (PreferAttraction preferAttraction : preferAttractions) {
            if (!request.attractionIds().contains(preferAttraction.getAttraction().getContentId())) {
                preferAttraction.setPrefer(false);
            }
        }

        // 입력 받은 것 중, 기존 DB에 없는 것들은 새로 추가.
        for (Long attractionId : request.attractionIds()) {
            if (!preferAttractionRepository.existsByTripPlanIdAndAttractionContentId(tripPlansId, attractionId)) {
                PreferAttraction preferAttraction = PreferAttraction.builder()
                        .tripPlan(tripPlanRepository.getReferenceById(tripPlansId))
                        .attraction(attractionRepository.getReferenceById(attractionId))
                        .isPrefer(true)
                        .build();
                preferAttractionRepository.save(preferAttraction);
            }
        }
    }


    @Override
    public void editRestaurantRecommendation(Long tripPlansId, SaveRestaurantRequestDTO request) {
        if (!tripPlanRepository.existsById(tripPlansId)) {
            throw new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND);
        }

        List<PreferRestaurant> preferRestaurants = preferRestaurantRepository.findAllByTripPlanId(tripPlansId);

        for (PreferRestaurant preferRestaurant : preferRestaurants) {
            if (!request.restaurantIds().contains(preferRestaurant.getRestaurant().getId())) {
                preferRestaurant.setPrefer(false);
            }
        }

        // 입력 받은 것 중, 기존 DB에 없는 것들은 새로 추가.
        for (Long restaurantId : request.restaurantIds()) {
            if (!preferRestaurantRepository.existsByTripPlanIdAndRestaurantId(tripPlansId, restaurantId)) {
                PreferRestaurant preferRestaurant = PreferRestaurant.builder()
                        .tripPlan(tripPlanRepository.getReferenceById(tripPlansId))
                        .restaurant(restaurantRepository.getReferenceById(restaurantId))
                        .isPrefer(true)
                        .build();
                preferRestaurantRepository.save(preferRestaurant);
            }
        }

    }

    @Override
    public void updateMemo(Long tripPlanId, Long itemId, UpdateMemoRequest memo) {
        TripScheduleItem tripScheduleItem = tripScheduleItemRepository.findById(itemId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._TRIP_SCHEDULE_ITEM_NOT_FOUND));

        if (!tripScheduleItem.getTripPlan().getId().equals(tripPlanId)) {
            throw new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND);
        }

        tripScheduleItem.updateMemo(memo.memo());
    }

    @Override
    public void updateTitle(Long tripPlanId, String title) {
        TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND));

        tripPlan.updateTitle(title);
    }

    private void createTripPlanFinal(Long tripPlanId) {
        TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND));

        List<Attraction> allAttractions = preferAttractionRepository.findByTripPlanIdAndIsPreferTrue(tripPlanId)
                .stream().map(PreferAttraction::getAttraction).collect(Collectors.toList());

        List<Restaurant> allRestaurants = preferRestaurantRepository.findByTripPlanIdAndIsPreferTrue(tripPlanId)
                .stream().map(PreferRestaurant::getRestaurant).collect(Collectors.toList());

        int totalDays = (int) DAYS.between(tripPlan.getStartDate(), tripPlan.getEndDate()) + 1;

        List<TripScheduleItem> scheduleItems = new ArrayList<>();

        for (int day = 0; day < totalDays; day++) {
            if (allAttractions.size() < 2 || allRestaurants.size() < 3) break;

            // 관광지 1개 선택 후 가장 가까운 1개 추가
            Attraction baseAttraction = allAttractions.remove(0);
            Attraction nearestAttraction = allAttractions.stream()
                    .min(Comparator.comparingDouble(a -> distance(
                            baseAttraction.getLatitude(), baseAttraction.getLongitude(),
                            a.getLatitude(), a.getLongitude())))
                    .orElse(null);
            allAttractions.remove(nearestAttraction);

            // 맛집 1개 선택 후 가장 가까운 2개 추가
            Restaurant baseRestaurant = allRestaurants.remove(0);
            List<Restaurant> closeRestaurants = allRestaurants.stream()
                    .sorted(Comparator.comparingDouble(r -> distance(
                            baseRestaurant.getLatitude(), baseRestaurant.getLongitude(),
                            r.getLatitude(), r.getLongitude())))
                    .limit(2).collect(Collectors.toList());
            allRestaurants.removeAll(closeRestaurants);

            int order = 0;
            scheduleItems.add(createScheduleItem(tripPlan, day + 1, order++, PlaceType.RESTAURANT, baseRestaurant));
            scheduleItems.add(createScheduleItem(tripPlan, day + 1, order++, PlaceType.ATTRACTION, baseAttraction));
            scheduleItems.add(createScheduleItem(tripPlan, day + 1, order++, PlaceType.RESTAURANT, closeRestaurants.get(0)));
            scheduleItems.add(createScheduleItem(tripPlan, day + 1, order++, PlaceType.ATTRACTION, nearestAttraction));
            scheduleItems.add(createScheduleItem(tripPlan, day + 1, order++, PlaceType.RESTAURANT, closeRestaurants.get(1)));
        }

        tripScheduleItemRepository.saveAll(scheduleItems);
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Earth radius in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
    private TripScheduleItem createScheduleItem(TripPlan tripPlan, int day, int order, PlaceType type, Object place) {
        TripScheduleItem.TripScheduleItemBuilder builder = TripScheduleItem.builder()
                .tripPlan(tripPlan)
                .dayNumber(day)
                .orderInDay(order)
                .placeType(type);

        if (type == PlaceType.ATTRACTION) {
            builder.attraction((Attraction) place);
        } else {
            builder.restaurant((Restaurant) place);
        }

        return builder.build();
    }



//    private double distance(double lat1, double lon1, double lat2, double lon2) {
//        double R = 6371; // Earth radius in km
//        double dLat = Math.toRadians(lat2 - lat1);
//        double dLon = Math.toRadians(lon2 - lon1);
//        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
//                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
//                        Math.sin(dLon/2) * Math.sin(dLon/2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//        return R * c; // in kilometers
//    }
//
//
//    private <T> T getRandom(List<T> list, Random random) {
//        return list.get(random.nextInt(list.size()));
//    }


}
