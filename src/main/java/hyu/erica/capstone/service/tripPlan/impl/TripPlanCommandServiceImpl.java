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
import hyu.erica.capstone.repository.PreferAttractionRepository;
import hyu.erica.capstone.repository.PreferRestaurantRepository;
import hyu.erica.capstone.repository.TripPlanRepository;
import hyu.erica.capstone.repository.TripScheduleItemRepository;
import hyu.erica.capstone.service.tripPlan.TripPlanCommandService;
import hyu.erica.capstone.web.dto.trip.request.SaveAttractionRequestDTO;
import hyu.erica.capstone.web.dto.trip.request.SaveRestaurantRequestDTO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TripPlanCommandServiceImpl implements TripPlanCommandService {

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

    private void createTripPlanFinal(Long tripPlanId) {
        TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND));

        List<PreferAttraction> attractions = preferAttractionRepository.findByTripPlanIdAndIsPreferTrue(tripPlanId);
        List<PreferRestaurant> restaurants = preferRestaurantRepository.findByTripPlanIdAndIsPreferTrue(tripPlanId);

        int totalDays = (int) DAYS.between(tripPlan.getStartDate(), tripPlan.getEndDate()) + 1;

        Random random = new Random();
        List<TripScheduleItem> scheduleItems = new ArrayList<>();

        for (int day = 1; day <= totalDays; day++) {
            // 랜덤 피벗 선택
            Attraction pivot = getRandom(attractions, random).getAttraction();
            Restaurant restaurantPivot = getRandom(restaurants, random).getRestaurant();

            List<Attraction> sortedAttractions = attractions.stream()
                    .map(PreferAttraction::getAttraction)
                    .sorted(Comparator.comparingDouble(a ->
                            distance(pivot.getLatitude(), pivot.getLongitude(), a.getLatitude(), a.getLongitude())))
                    .limit(3)
                    .toList();

            List<Restaurant> sortedRestaurants = restaurants.stream()
                    .map(PreferRestaurant::getRestaurant)
                    .sorted(Comparator.comparingDouble(r ->
                            distance(restaurantPivot.getLatitude(), restaurantPivot.getLongitude(),
                                    r.getLatitude(), r.getLongitude())))
                    .limit(3)
                    .toList();

            int order = 1;
            for (int i = 0; i < 3; i++) {
                // 여행지 먼저
                if (i < sortedAttractions.size()) {
                    scheduleItems.add(TripScheduleItem.builder()
                            .tripPlan(tripPlan)
                            .dayNumber(day)
                            .orderInDay(order++)
                            .placeType(PlaceType.ATTRACTION)
                            .attraction(sortedAttractions.get(i))
                            .build());
                }

                // 식당 다음
                if (i < sortedRestaurants.size()) {
                    scheduleItems.add(TripScheduleItem.builder()
                            .tripPlan(tripPlan)
                            .dayNumber(day)
                            .orderInDay(order++)
                            .placeType(PlaceType.RESTAURANT)
                            .restaurant(sortedRestaurants.get(i))
                            .build());
                }
            }
        }


        tripScheduleItemRepository.saveAll(scheduleItems);
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Earth radius in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c; // in kilometers
    }


    private <T> T getRandom(List<T> list, Random random) {
        return list.get(random.nextInt(list.size()));
    }


}
