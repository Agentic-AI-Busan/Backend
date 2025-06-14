package hyu.erica.capstone.service.tripPlan.impl;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.domain.Attraction;
import hyu.erica.capstone.domain.Restaurant;
import hyu.erica.capstone.domain.TripPlan;
import hyu.erica.capstone.domain.TripScheduleItem;
import hyu.erica.capstone.domain.mapping.PreferAttraction;
import hyu.erica.capstone.domain.mapping.PreferRestaurant;
import hyu.erica.capstone.repository.AttractionRepository;
import hyu.erica.capstone.repository.PreferAttractionRepository;
import hyu.erica.capstone.repository.PreferRestaurantRepository;
import hyu.erica.capstone.repository.RestaurantRepository;
import hyu.erica.capstone.repository.TripPlanRepository;
import hyu.erica.capstone.repository.TripScheduleItemRepository;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.service.tripPlan.TripPlanQueryService;
import hyu.erica.capstone.web.dto.tripPlan.response.TripPlanResultResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.attraction.AttractionDetailResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.attraction.AttractionListResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.attraction.AttractionSearchResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.restaurant.RestaurantDetailResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.restaurant.RestaurantListResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.restaurant.RestaurantSearchResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TripPlanQueryServiceImpl implements TripPlanQueryService {

    private final TripPlanRepository tripPlanRepository;
    private final AttractionRepository attractionRepository;
    private final RestaurantRepository restaurantRepository;
    private final PreferRestaurantRepository preferRestaurantRepository;
    private final PreferAttractionRepository preferAttractionRepository;
    private final TripScheduleItemRepository tripScheduleItemRepository;
    private final UserRepository userRepository;

    @Override
    public TripPlanResultResponseDTO getTripPlan(Long tripPlanId) {
        TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND));

        List<TripScheduleItem> tripScheduleItems = tripScheduleItemRepository.findAllByTripPlanId(tripPlanId);

        return TripPlanResultResponseDTO.of(tripPlan, tripScheduleItems);
    }

    @Override
    public TripPlanResultResponseDTO getTripPlanByDay(Long tripPlanId, int day) {
        TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND));
        List<TripScheduleItem> tripScheduleItems = tripScheduleItemRepository.findAllByTripPlanIdAndDayNumber(tripPlanId, day);

        return TripPlanResultResponseDTO.of(tripPlan, tripScheduleItems);
    }

    @Override
    public AttractionListResponseDTO getRecommendAttractions(Long tripPlanId) {
        if (!tripPlanRepository.existsById(tripPlanId) ||
                preferAttractionRepository.findAllByTripPlanId(tripPlanId).isEmpty())
            throw new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND);

        List<PreferAttraction> preferAttractions = preferAttractionRepository.findAllByTripPlanId(tripPlanId);

        List<Attraction> attractions = preferAttractions.stream()
                .map(PreferAttraction::getAttraction)
                .toList();

        return AttractionListResponseDTO.of(attractions);
    }

    @Override
    public AttractionDetailResponseDTO getRecommendAttractionDetail(Long attractionId) {
        Attraction attraction = attractionRepository.findById(attractionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._ATTRACTION_NOT_FOUND));

        return AttractionDetailResponseDTO.of(attraction);
    }

    @Override
    public AttractionSearchResponseDTO searchRecommendAttraction(String keyword) {
        List<Attraction> attractions = attractionRepository.findByContentNameContaining(keyword);
        return AttractionSearchResponseDTO.of(attractions);
    }

    @Override
    public RestaurantListResponseDTO getRecommendRestaurants(Long tripPlanId) {
        if (!tripPlanRepository.existsById(tripPlanId)  ||
                preferRestaurantRepository.findAllByTripPlanId(tripPlanId).isEmpty())
            throw new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND);

        List<PreferRestaurant> preferRestaurants = preferRestaurantRepository.findAllByTripPlanId(tripPlanId);

        List<Restaurant> restaurants = preferRestaurants.stream()
                .map(PreferRestaurant::getRestaurant)
                .toList();

        return RestaurantListResponseDTO.of(restaurants);
    }

    @Override
    public RestaurantDetailResponseDTO getRecommendRestaurantDetail(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._RESTAURANT_NOT_FOUND));

        return RestaurantDetailResponseDTO.of(restaurant);
    }

    @Override
    public RestaurantSearchResponseDTO searchRecommendRestaurant(String keyword) {
        List<Restaurant> restaurants = restaurantRepository.findByRestaurantNameContaining(keyword);
        return RestaurantSearchResponseDTO.of(restaurants);
    }

    @Override
    public AttractionListResponseDTO getPopularAttractions() {
        List<Attraction> attractions = attractionRepository.findRandom20();
        return AttractionListResponseDTO.of(attractions);
    }
}
