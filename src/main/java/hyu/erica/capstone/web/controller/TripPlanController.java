package hyu.erica.capstone.web.controller;

import hyu.erica.capstone.api.ApiResponse;
import hyu.erica.capstone.web.dto.trip.request.AdditionalInfoRequestDTO;
import hyu.erica.capstone.web.dto.trip.request.PreferActivitiesRequestDTO;
import hyu.erica.capstone.web.dto.trip.request.TripPeriodRequestDTO;
import hyu.erica.capstone.web.dto.trip.response.AdditionalInfoResponseDTO;
import hyu.erica.capstone.web.dto.trip.response.PreferActivitiesResponseDTO;
import hyu.erica.capstone.web.dto.trip.response.TripPeriodResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Trip", description = "여행 관련 API")
@CrossOrigin
@RestController
@RequestMapping("/api/trip-plans/{tripPlansId}")
public class TripPlanController {

    // 여행 기간 입력
    @PostMapping("/period")
    public ApiResponse<?> inputPeriod(
            @PathVariable Long tripPlansId,
            @RequestBody TripPeriodRequestDTO request) {
        // 여행 기간 입력
        return null;
    }

    @GetMapping("/period")
    public ApiResponse<TripPeriodResponseDTO> getPeriod(
            @PathVariable Long tripPlansId) {
        // 여행 기간 입력
        return null;
    }

    // 선호 활동 입력
    @PostMapping("/activities")
    public ApiResponse<?> inputActivities(
            @PathVariable Long tripPlansId,
            @RequestBody PreferActivitiesRequestDTO request) {
        // 선호 활동 입력
        return null;
    }

    @GetMapping("/activities")
    public ApiResponse<PreferActivitiesResponseDTO> getActivities(
            @PathVariable Long tripPlansId) {
        // 선호 활동 입력
        return null;
    }

    // 추가적인 요구 사항 입력
    @PostMapping("/additional")
    public ApiResponse<?> inputAdditional(
            @PathVariable Long tripPlansId,
            @RequestBody AdditionalInfoRequestDTO request) {
        // 추가적인 요구 사항 입력
        return null;
    }

    @GetMapping("/additional")
    public ApiResponse<AdditionalInfoResponseDTO> getAdditional(
            @PathVariable Long tripPlansId) {
        // 추가적인 요구 사항 입력
        return null;
    }

    // 최종 입력 ( AI API 호출)
    @GetMapping("/final")
    public ApiResponse<?> finalInput(
            @PathVariable Long tripPlansId) {
        // 최종 입력 ( AI API 호출)
        return null;
    }

    // 선택지 확인 (여행지)
    @GetMapping("/attractions")
    public ApiResponse<?> checkPlaces(
            @PathVariable Long tripPlansId) {
        // 선택지 확인 (여행지)
        return null;
    }

    // 선택지 상세 보기 (여행지)
    @GetMapping("/attractions/{attractionId}")
    public ApiResponse<?> viewPlaceDetail(
            @PathVariable Long tripPlansId,
            @PathVariable Long attractionId) {
        // 선택지 상세 보기 (여행지)
        return null;
    }

    // 여행지 키워드 검색
    @GetMapping("/attractions/search")
    public ApiResponse<?> searchPlace(
            @PathVariable Long tripPlansId,
            @RequestParam String keyword) {
        // 여행지 키워드 검색
        return null;
    }

    // 여행지 최종 선택
    @PostMapping("/attractions/final")
    public ApiResponse<?> finalPlace(
            @PathVariable Long tripPlansId) {
        // 여행지 최종 선택
        return null;
    }

    // 선택지 확인 (음식점)
    @GetMapping("/restaurants")
    public ApiResponse<?> checkRestaurants(
            @PathVariable Long tripPlansId) {
        // 선택지 확인 (음식점)
        return null;
    }


    // 선택지 상세 보기 (음식점)
    @GetMapping("/restaurants/{restaurantId}")
    public ApiResponse<?> viewRestaurantDetail(
            @PathVariable Long tripPlansId,
            @PathVariable Long restaurantId) {
        // 선택지 상세 보기 (음식점)
        return null;
    }


    // 음식점 키워드 검색
    @GetMapping("/restaurants/search")
    public ApiResponse<?> searchRestaurant(
            @PathVariable Long tripPlansId,
            @RequestParam String keyword) {
        // 음식점 키워드 검색
        return null;
    }


    // 음식점 최종 선택
    @PostMapping("/restaurants/final")
    public ApiResponse<?> finalRestaurant(
            @PathVariable Long tripPlansId) {
        // 음식점 최종 선택
        return null;
    }


}
