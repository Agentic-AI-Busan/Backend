package hyu.erica.capstone.service.tripPlan.impl;

import static java.time.temporal.ChronoUnit.DAYS;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.domain.Attraction;
import hyu.erica.capstone.domain.Restaurant;
import hyu.erica.capstone.domain.TripPlan;
import hyu.erica.capstone.domain.TripScheduleItem;
import hyu.erica.capstone.domain.enums.PlaceType;
import hyu.erica.capstone.domain.mapping.PreferAttraction;
import hyu.erica.capstone.domain.mapping.PreferRestaurant;
import hyu.erica.capstone.repository.AttractionRepository;
import hyu.erica.capstone.repository.PreferAttractionRepository;
import hyu.erica.capstone.repository.PreferRestaurantRepository;
import hyu.erica.capstone.repository.RestaurantRepository;
import hyu.erica.capstone.repository.TripPlanRepository;
import hyu.erica.capstone.repository.TripScheduleItemRepository;
import hyu.erica.capstone.service.tripPlan.TripPlanCommandService;
import hyu.erica.capstone.web.dto.trip.request.SaveAttractionRequestDTO;
import hyu.erica.capstone.web.dto.trip.request.SaveRestaurantRequestDTO;
import hyu.erica.capstone.web.dto.tripPlan.request.UpdateAllScheduleOrderRequest;
import hyu.erica.capstone.web.dto.tripPlan.request.UpdateAllScheduleOrderRequest.ScheduleOrderItemDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TripPlanCommandServiceImpl implements TripPlanCommandService {

    private final AttractionRepository attractionRepository;
    private final RestaurantRepository restaurantRepository;

    private final PreferAttractionRepository preferAttractionRepository;
    private final PreferRestaurantRepository preferRestaurantRepository;
    private final TripScheduleItemRepository tripScheduleItemRepository;
    private final TripPlanRepository tripPlanRepository;

    @Override
    public Long confirmAttractionRecommendation(Long tripPlanId, SaveAttractionRequestDTO request) {
        List<PreferAttraction> preferAttractions = preferAttractionRepository.findAllByTripPlanId(tripPlanId);

        for (PreferAttraction preferAttraction : preferAttractions) {
            if (!request.attractionIds().contains(preferAttraction.getAttraction().getContentId())) {
                preferAttraction.setPrefer(false);
            }
        }

        return tripPlanId;
    }

    @Override
    public Long confirmRestaurantRecommendation(Long tripPlanId, SaveRestaurantRequestDTO request) {
        List<PreferRestaurant> preferRestaurants = preferRestaurantRepository.findAllByTripPlanId(tripPlanId);

        for (PreferRestaurant preferRestaurant : preferRestaurants) {
            if (!request.restaurantIds().contains(preferRestaurant.getRestaurant().getId())) {
                preferRestaurant.setPrefer(false);
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
                item.updateMemo(dto.memo());
                updatedIds.add(dto.id());
            } else {

                TripScheduleItem newItem = TripScheduleItem.builder()
                        .tripPlan(tripPlanRepository.getReferenceById(request.tripPlanId()))
                        .dayNumber(dto.dayNumber())
                        .orderInDay(dto.orderInDay())
                        .placeType(dto.placeType())
                        .attraction(dto.attractionId() != null ? attractionRepository.getReferenceById(dto.attractionId()) : null)
                        .restaurant(dto.restaurantId() != null ? restaurantRepository.getReferenceById(dto.restaurantId()) : null)
                        .memo(dto.memo())
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

    private void createTripPlanFinal(Long tripPlanId) {
        TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND));

        List<Attraction> allAttractions = preferAttractionRepository.findByTripPlanIdAndIsPreferTrue(tripPlanId)
                .stream().map(PreferAttraction::getAttraction).collect(Collectors.toList());

        List<Restaurant> allRestaurants = preferRestaurantRepository.findByTripPlanIdAndIsPreferTrue(tripPlanId)
                .stream().map(PreferRestaurant::getRestaurant).collect(Collectors.toList());

        int totalDays = (int) DAYS.between(tripPlan.getStartDate(), tripPlan.getEndDate()) + 1;

        // 섞기
        Collections.shuffle(allAttractions);
        Collections.shuffle(allRestaurants);

        // 필요한 개수만큼만 사용 (초과할 경우 대비)
        int maxAttractions = Math.min(totalDays * 2, allAttractions.size());
        int maxRestaurants = Math.min(totalDays * 2, allRestaurants.size());

        List<Attraction> usableAttractions = allAttractions.subList(0, maxAttractions);
        List<Restaurant> usableRestaurants = allRestaurants.subList(0, maxRestaurants);

        List<TripScheduleItem> scheduleItems = new ArrayList<>();

        for (int day = 0; day < totalDays; day++) {
            int order = 1;
            int baseIndex = day * 2;

            // 각 일자마다 attraction 2개, restaurant 2개가 있어야 함
            if (baseIndex + 1 < usableAttractions.size() && baseIndex + 1 < usableRestaurants.size()) {
                scheduleItems.add(createScheduleItem(tripPlan, day + 1, order++, PlaceType.ATTRACTION, usableAttractions.get(baseIndex)));
                scheduleItems.add(createScheduleItem(tripPlan, day + 1, order++, PlaceType.RESTAURANT, usableRestaurants.get(baseIndex)));
                scheduleItems.add(createScheduleItem(tripPlan, day + 1, order++, PlaceType.ATTRACTION, usableAttractions.get(baseIndex + 1)));
                scheduleItems.add(createScheduleItem(tripPlan, day + 1, order++, PlaceType.RESTAURANT, usableRestaurants.get(baseIndex + 1)));
            }
        }

        tripScheduleItemRepository.saveAll(scheduleItems);
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
