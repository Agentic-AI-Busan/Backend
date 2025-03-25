package hyu.erica.capstone.web.controller;

import hyu.erica.capstone.api.ApiResponse;
import hyu.erica.capstone.api.code.status.SuccessStatus;
import hyu.erica.capstone.service.tripPlan.TripPlanCommandService;
import hyu.erica.capstone.service.tripPlan.TripPlanQueryService;
import hyu.erica.capstone.web.dto.trip.request.AdditionalInfoRequestDTO;
import hyu.erica.capstone.web.dto.trip.request.PreferActivitiesRequestDTO;
import hyu.erica.capstone.web.dto.trip.request.SaveAttractionRequestDTO;
import hyu.erica.capstone.web.dto.trip.request.SaveRestaurantRequestDTO;
import hyu.erica.capstone.web.dto.trip.request.TripPeriodRequestDTO;
import hyu.erica.capstone.web.dto.trip.response.AdditionalInfoResponseDTO;
import hyu.erica.capstone.web.dto.trip.response.PreferActivitiesResponseDTO;
import hyu.erica.capstone.web.dto.trip.response.TripPeriodResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/trip-plans")
@RequiredArgsConstructor
public class TripPlanController {

    private final TripPlanQueryService tripPlanQueryService;
    private final TripPlanCommandService tripPlanCommandService;


    // 선택지 확인 (여행지)
    @Tag(name = "선택지 확인", description = "선택지 확인 API")
    @Operation(summary = "선택지 (여행지) 확인", description = """
            ### 선택지 확인 API
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
                  """)
    @GetMapping("/{tripPlansId}/attractions")
    public ApiResponse<?> checkPlaces(
            @PathVariable Long tripPlansId) {
        // 선택지 확인 (여행지)
        return ApiResponse.onSuccess(SuccessStatus._OK, tripPlanQueryService.getRecommendAttractions(tripPlansId));
    }

    // 선택지 상세 보기 (여행지)
    @Tag(name = "선택지 확인", description = "선택지 확인 API")
    @Operation(summary = "선택지 (여행지) 상세 보기", description = """
            ### 선택지 상세 보기 API
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
            - attractionId: 여행지 ID
                  """)
    @GetMapping("/attractions/{attractionId}")
    public ApiResponse<?> viewPlaceDetail(
            @PathVariable Long attractionId) {
        // 선택지 상세 보기 (여행지)
        return ApiResponse.onSuccess(SuccessStatus._OK, tripPlanQueryService.getRecommendAttractionDetail(attractionId));
    }

    // 여행지 키워드 검색
    @Tag(name = "선택지 확인", description = "선택지 확인 API")
    @Operation(summary = "여행지 키워드 검색", description = """
            ### 여행지 키워드 검색 API
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
            
            ### Request Param
            - keyword: 검색 키워드
                  """)
    @GetMapping("/attractions/search")
    public ApiResponse<?> searchPlace(
            @RequestParam String keyword) {
        // 여행지 키워드 검색
        return ApiResponse.onSuccess(SuccessStatus._OK, tripPlanQueryService.searchRecommendAttraction(keyword));
    }

    // 여행지 최종 선택
    @Tag(name = "선택지 확인", description = "선택지 확인 API")
    @Operation(summary = "여행지 최종 선택", description = """
            ### 여행지 최종 선택 API
            
            ### Path Variables
            - tripPlansIds: 여행 계획 ID (List)
            
                  """)
    @PostMapping("/{tripPlansId}/attractions/final")
    public ApiResponse<?> finalPlace(
            @PathVariable Long tripPlansId,
            @RequestBody SaveAttractionRequestDTO request) {
        // 여행지 최종 선택
        return ApiResponse.onSuccess(SuccessStatus._OK, tripPlanCommandService.confirmAttractionRecommendation(tripPlansId, request));
    }

    // 선택지 확인 (음식점)
    @Tag(name = "선택지 확인", description = "선택지 확인 API")
    @Operation(summary = "선택지 (음식점) 확인", description = """
            ### 선택지 확인 API
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
                  """)
    @GetMapping("/{tripPlansId}/restaurants")
    public ApiResponse<?> checkRestaurants(
            @PathVariable Long tripPlansId) {
        // 선택지 확인 (음식점)
        return ApiResponse.onSuccess(SuccessStatus._OK, tripPlanQueryService.getRecommendRestaurants(tripPlansId));
    }


    // 선택지 상세 보기 (음식점)
    @Tag(name = "선택지 확인", description = "선택지 확인 API")
    @Operation(summary = "선택지 (음식점) 상세 보기", description = """
            ### 선택지 상세 보기 API
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
            - restaurantId: 음식점 ID
                  """)
    @GetMapping("/restaurants/{restaurantId}")
    public ApiResponse<?> viewRestaurantDetail(
            @PathVariable Long restaurantId) {
        // 선택지 상세 보기 (음식점)
        return ApiResponse.onSuccess(SuccessStatus._OK, tripPlanQueryService.getRecommendRestaurantDetail(restaurantId));
    }


    // 음식점 키워드 검색
    @Tag(name = "선택지 확인", description = "선택지 확인 API")
    @Operation(summary = "음식점 키워드 검색", description = """
            ### 음식점 키워드 검색 API
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
            
            ### Request Param
            - keyword: 검색 키워드
                  """)
    @GetMapping("/restaurants/search")
    public ApiResponse<?> searchRestaurant(
            @RequestParam String keyword) {
        // 음식점 키워드 검색
        return ApiResponse.onSuccess(SuccessStatus._OK, tripPlanQueryService.searchRecommendRestaurant(keyword));
    }


    // 음식점 최종 선택
    @Tag(name = "선택지 확인", description = "선택지 확인 API")
    @Operation(summary = "음식점 최종 선택", description = """
            ### 음식점 최종 선택 API
            
            ### Path Variables
            - tripPlansIds: 여행 계획 ID (List)
                  """)
    @PostMapping("/{tripPlansId}/restaurants/final")
    public ApiResponse<?> finalRestaurant(
            @PathVariable Long tripPlansId,
            @RequestBody SaveRestaurantRequestDTO request) {
        // 음식점 최종 선택
        return ApiResponse.onSuccess(SuccessStatus._OK, tripPlanCommandService.confirmRestaurantRecommendation(tripPlansId, request));
    }


}
