package hyu.erica.capstone.service.tripPlan;

import hyu.erica.capstone.web.dto.tripPlan.response.AttractionDetailResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.AttractionListResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.RestaurantListResponseDTO;

public interface TripPlanQueryService {

    // 추천 여행지 리스트 조회
    AttractionListResponseDTO getRecommendAttractions(Long tripPlanId);

    // 추천 여행지 상세 조회
    AttractionDetailResponseDTO getRecommendAttractionDetail(Long attractionId);

    // 추천 여행지 키워드 검색
    AttractionDetailResponseDTO searchRecommendAttraction(String keyword);

    // 추천 식당 리스트 조회
    RestaurantListResponseDTO getRecommendRestaurants(Long tripPlanId);

    // 추천 식당 상세 조회
    AttractionDetailResponseDTO getRecommendRestaurantDetail(Long restaurantId);

    // 추천 식당 키워드 검색
    AttractionDetailResponseDTO searchRecommendRestaurant(String keyword);
}
