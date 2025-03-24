package hyu.erica.capstone.web.controller;


import hyu.erica.capstone.api.ApiResponse;
import hyu.erica.capstone.api.code.status.SuccessStatus;
import hyu.erica.capstone.service.tripPlan.TripPlanQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[개발 전] 여행 계획", description = "여행 계획 관련 API")
@CrossOrigin
@RestController
@RequestMapping("/api/trip-plans")
@RequiredArgsConstructor
public class PlanController {

    private final TripPlanQueryService tripPlanQueryService;

    // 일정 제공
    @Operation(summary = "일정 제공", description = """
            ### 일정 제공 API 
            일정 확인을 위한 API입니다. 조회 하려는 여행 계획 ID를 Path Variable로 입력해주세요.
            
            ### Path Variables
            - tripPlanId: 여행 계획 ID
            
            ### Response
            - tripPlanId: 여행 계획 ID
            
                  """)
    @GetMapping("/{tripPlanId}")
    public ApiResponse<?> getPlan(@PathVariable Long tripPlanId) {
        // 일정 제공
        return ApiResponse.onSuccess(SuccessStatus._OK, tripPlanQueryService.getTripPlan(tripPlanId));
    }

    // Result 화면 위한 API
    @GetMapping("/result")
    public ApiResponse<?> getResult() {
        // Result 화면 위한 API
        return null;
    }

    // 채팅 입력 -> AI API 호출
    @PostMapping("/chat")
    public ApiResponse<?> chat() {
        // 채팅 입력 -> AI API 호출
        return null;
    }

    // 일정 편집 (순서 변경)
    @PutMapping("")
    public ApiResponse<?> editPlan() {
        // 일정 편집 (순서 변경)
        return null;
    }

    // 일정 편집 (삭제)
    @DeleteMapping()
    public ApiResponse<?> deletePlan() {
        // 일정 편집 (삭제)
        return null;
    }

    // 일정 편집 (추가)
    @PostMapping()
    public ApiResponse<?> addPlan() {
        // 일정 편집 (추가)
        return null;
    }

    // 최종 저장
    @PostMapping("/final")
    public ApiResponse<?> finalSave() {
        // 최종 저장
        return null;
    }
}
