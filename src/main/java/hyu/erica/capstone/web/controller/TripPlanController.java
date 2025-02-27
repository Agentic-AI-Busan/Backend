package hyu.erica.capstone.web.controller;

import hyu.erica.capstone.api.ApiResponse;
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
@RequestMapping("/api/trip-plans/{tripPlansId}")
public class TripPlanController {

    // 여행 기간 입력
    @Tag(name = "Trip", description = "여행 관련 API")
    @Operation(summary = "여행 기간 입력", description = """
            ### 여행 기간을 입력합니다.
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
            
            ### Request Body
            - startDate: 여행 시작 날짜
            - startHour: 여행 시작 시간
            - endDate: 여행 종료 날짜
            - endHour: 여행 종료 시간
                  """)
    @PostMapping("/period")
    public ApiResponse<?> inputPeriod(
            @PathVariable Long tripPlansId,
            @RequestBody TripPeriodRequestDTO request) {
        // 여행 기간 입력
        return null;
    }

    @Tag(name = "Trip", description = "여행 관련 API")
    @Operation(summary = "여행 기간 조회", description = """
            ### 여행 기간을 조회합니다.
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
                  """)
    @GetMapping("/period")
    public ApiResponse<TripPeriodResponseDTO> getPeriod(
            @PathVariable Long tripPlansId) {
        // 여행 기간 입력
        return null;
    }

    // 선호 활동 입력
    @Tag(name = "Trip", description = "여행 관련 API")
    @Operation(summary = "선호 활동 입력", description = """
            ### 선호 활동을 입력합니다.
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
            
            ### Request Body
            - activities: 선호 활동 (String)
                  """)
    @PostMapping("/activities")
    public ApiResponse<?> inputActivities(
            @PathVariable Long tripPlansId,
            @RequestBody PreferActivitiesRequestDTO request) {
        // 선호 활동 입력
        return null;
    }

    @Tag(name = "Trip", description = "여행 관련 API")
    @Operation(summary = "선호 활동 조회", description = """
            ### 선호 활동을 조회합니다.
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
                  """)
    @GetMapping("/activities")
    public ApiResponse<PreferActivitiesResponseDTO> getActivities(
            @PathVariable Long tripPlansId) {
        // 선호 활동 입력
        return null;
    }

    // 추가적인 요구 사항 입력
    @Tag(name = "Trip", description = "여행 관련 API")
    @Operation(summary = "추가적인 요구 사항 입력", description = """
            ### 추가적인 요구 사항을 입력합니다.
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
            
            ### Request Body
            - additionalInfo: 추가적인 요구 사항 (String)
                  """)
    @PostMapping("/additional")
    public ApiResponse<?> inputAdditional(
            @PathVariable Long tripPlansId,
            @RequestBody AdditionalInfoRequestDTO request) {
        // 추가적인 요구 사항 입력
        return null;
    }

    @Tag(name = "Trip", description = "여행 관련 API")
    @Operation(summary = "추가적인 요구 사항 조회", description = """
            ### 추가적인 요구 사항을 조회합니다.
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
                  """)
    @GetMapping("/additional")
    public ApiResponse<AdditionalInfoResponseDTO> getAdditional(
            @PathVariable Long tripPlansId) {
        // 추가적인 요구 사항 입력
        return null;
    }

    @Tag(name = "Trip", description = "여행 관련 API")
    @Operation(summary = "일정 제공", description = """
            ### 최종적으로 입력한 정보를 바탕으로 일정을 제공합니다.
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
                  """)
    // 최종 입력 ( AI API 호출)
    @GetMapping("/final")
    public ApiResponse<?> finalInput(
            @PathVariable Long tripPlansId) {
        // 최종 입력 ( AI API 호출)
        return null;
    }

    /* ------------------------------------------------------------------------------------------------------ */

    // 선택지 확인 (여행지)
    @Tag(name = "선택지 확인", description = "선택지 확인 API")
    @Operation(summary = "선택지 (여행지) 확인", description = """
            ### 선택지 확인 API
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
                  """)
    @GetMapping("/attractions")
    public ApiResponse<?> checkPlaces(
            @PathVariable Long tripPlansId) {
        // 선택지 확인 (여행지)
        return null;
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
            @PathVariable Long tripPlansId,
            @PathVariable Long attractionId) {
        // 선택지 상세 보기 (여행지)
        return null;
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
            @PathVariable Long tripPlansId,
            @RequestParam String keyword) {
        // 여행지 키워드 검색
        return null;
    }

    // 여행지 최종 선택
    @Tag(name = "선택지 확인", description = "선택지 확인 API")
    @Operation(summary = "여행지 최종 선택", description = """
            ### 여행지 최종 선택 API
            
            ### Path Variables
            - tripPlansIds: 여행 계획 ID (List)
            
                  """)
    @PostMapping("/attractions/final")
    public ApiResponse<?> finalPlace(
            @PathVariable Long tripPlansId,
            @RequestBody SaveAttractionRequestDTO request) {
        // 여행지 최종 선택
        return null;
    }

    // 선택지 확인 (음식점)
    @Tag(name = "선택지 확인", description = "선택지 확인 API")
    @Operation(summary = "선택지 (음식점) 확인", description = """
            ### 선택지 확인 API
            
            ### Path Variables
            - tripPlansId: 여행 계획 ID
                  """)
    @GetMapping("/restaurants")
    public ApiResponse<?> checkRestaurants(
            @PathVariable Long tripPlansId) {
        // 선택지 확인 (음식점)
        return null;
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
            @PathVariable Long tripPlansId,
            @PathVariable Long restaurantId) {
        // 선택지 상세 보기 (음식점)
        return null;
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
            @PathVariable Long tripPlansId,
            @RequestParam String keyword) {
        // 음식점 키워드 검색
        return null;
    }


    // 음식점 최종 선택
    @Tag(name = "선택지 확인", description = "선택지 확인 API")
    @Operation(summary = "음식점 최종 선택", description = """
            ### 음식점 최종 선택 API
            
            ### Path Variables
            - tripPlansIds: 여행 계획 ID (List)
                  """)
    @PostMapping("/restaurants/final")
    public ApiResponse<?> finalRestaurant(
            @PathVariable Long tripPlansId,
            @RequestBody SaveRestaurantRequestDTO request) {
        // 음식점 최종 선택
        return null;
    }


}
