package hyu.erica.capstone.controller;


import hyu.erica.capstone.api.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Plan", description = "여행 계획 관련 API")
@CrossOrigin
@RestController
@RequestMapping("/api/plans")
public class PlanController {

    // 일정 제공
    @GetMapping("/{planId}")
    public ApiResponse<?> getPlan() {
        // 일정 제공
        return null;
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
