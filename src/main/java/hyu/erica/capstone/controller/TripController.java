package hyu.erica.capstone.controller;

import hyu.erica.capstone.api.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Trip", description = "여행 관련 API")
@CrossOrigin
@RestController
@RequestMapping("/api/trips")
public class TripController {

    // 여행 기간 입력
    @PostMapping("/period")
    public ApiResponse<?> inputPeriod() {
        // 여행 기간 입력
        return null;
    }

    // 선호 활동 입력
    @PostMapping("/activities")
    public ApiResponse<?> inputActivities() {
        // 선호 활동 입력
        return null;
    }

    // 최종 입력 ( AI API 호출)
    @PostMapping("/final")
    public ApiResponse<?> finalInput() {
        // 최종 입력 ( AI API 호출)
        return null;
    }

    // 선택지 확인 (여행지)
    @GetMapping("/attractions")
    public ApiResponse<?> checkPlaces() {
        // 선택지 확인 (여행지)
        return null;
    }

    // 선택지 상세 보기 (여행지)
    @GetMapping("/attractions/{attractionId}")
    public ApiResponse<?> viewPlaceDetail() {
        // 선택지 상세 보기 (여행지)
        return null;
    }

    // 여행지 키워드 검색
    @GetMapping("/attractions/search")
    public ApiResponse<?> searchPlace() {
        // 여행지 키워드 검색
        return null;
    }

    // 여행지 최종 선택
    @PostMapping("/attractions/final")
    public ApiResponse<?> finalPlace() {
        // 여행지 최종 선택
        return null;
    }

    // 선택지 확인 (음식점)
    @GetMapping("/restaurants")
    public ApiResponse<?> checkRestaurants() {
        // 선택지 확인 (음식점)
        return null;
    }


    // 선택지 상세 보기 (음식점)
    @GetMapping("/restaurants/{restaurantId}")
    public ApiResponse<?> viewRestaurantDetail() {
        // 선택지 상세 보기 (음식점)
        return null;
    }


    // 음식점 키워드 검색
    @GetMapping("/restaurants/search")
    public ApiResponse<?> searchRestaurant() {
        // 음식점 키워드 검색
        return null;
    }


    // 음식점 최종 선택
    @PostMapping("/restaurants/final")
    public ApiResponse<?> finalRestaurant() {
        // 음식점 최종 선택
        return null;
    }


}
