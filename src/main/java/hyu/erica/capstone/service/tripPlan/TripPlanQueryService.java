package hyu.erica.capstone.service.tripPlan;

import hyu.erica.capstone.web.dto.tripPlan.response.attraction.AttractionDetailResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.attraction.AttractionListResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.attraction.AttractionSearchResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.restaurant.RestaurantDetailResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.restaurant.RestaurantListResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.restaurant.RestaurantSearchResponseDTO;

public interface TripPlanQueryService {

    // 추천 여행지 리스트 조회
    AttractionListResponseDTO getRecommendAttractions(Long tripPlanId);

    // 추천 여행지 상세 조회
    AttractionDetailResponseDTO getRecommendAttractionDetail(Long attractionId);

    // 추천 여행지 키워드 검색
    AttractionSearchResponseDTO searchRecommendAttraction(String keyword);

    // 추천 식당 리스트 조회
    RestaurantListResponseDTO getRecommendRestaurants(Long tripPlanId);

    // 추천 식당 상세 조회
    RestaurantDetailResponseDTO getRecommendRestaurantDetail(Long restaurantId);

    // 추천 식당 키워드 검색
    RestaurantSearchResponseDTO searchRecommendRestaurant(String keyword);
}
