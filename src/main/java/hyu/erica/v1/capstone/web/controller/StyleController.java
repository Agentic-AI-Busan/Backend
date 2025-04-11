package hyu.erica.v1.capstone.web.controller;


import hyu.erica.v1.capstone.api.ApiResponse;
import hyu.erica.v1.capstone.api.code.status.SuccessStatus;
import hyu.erica.v1.capstone.security.utils.SecurityUtils;
import hyu.erica.v1.capstone.service.style.StyleCommandService;
import hyu.erica.v1.capstone.service.style.StyleQueryService;
import hyu.erica.v1.capstone.web.dto.style.request.UserStyleRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "여행 스타일", description = "여행 스타일 관련 API")
@CrossOrigin
@RestController
@RequestMapping("/api/styles")
@RequiredArgsConstructor
public class StyleController {

    private final StyleQueryService styleQueryService;
    private final StyleCommandService styleCommandService;

    @Operation(summary = "[선호 여행 스타일 작성 시작]", description = """
            ### 선호 여행 스타일 작성을 위한 정보를 가져옵니다. \n
            발급 받은 styleId를 통해 수시로 선호 여행 스타일을 임시 저장 할 수 있습니다. \n
            
            ### Response Body
            - name : 회원 이름
            - styleId : 스타일 ID (해당 스타일 ID로 선호 여행 스타일을 조회, 수정할 수 있습니다.)
            """)
    @GetMapping("")
    public ApiResponse<?> getUserPlanStyle() {
        return ApiResponse.of(SuccessStatus._OK, styleCommandService.initStyle(SecurityUtils.getCurrentUserId()));
    }

    // 선호 여행 스타일 입력
    @Operation(summary = "[선호 여행 스타일 입력 및 수정]", description = """
            ### 선호 여행 스타일을 입력 및 수정합니다. 모든 값은 필수가 아닙니다. \n
            styleId를 통해 특정 선호 여행 스타일을 수정할 수 있습니다. \n
            
            ### Request Body
            - city: 도시
            - startDate: 여행 시작 날짜
            - endDate: 여행 종료 날짜
            - preferActivity: 선호하는 활동
            - requirement: 요구사항
            """)
    @PostMapping("/{styleId}")
    public ApiResponse<?> saveUserPlanStyle(@RequestBody UserStyleRequestDTO request,
                                            @PathVariable Long styleId) {
        return ApiResponse.onSuccess(SuccessStatus._OK, styleCommandService.updateStyle(SecurityUtils.getCurrentUserId(), styleId, request));
    }

    // 선호 여행 스타일 조회
    @Operation(summary = "[선호 여행 스타일 조회]", description = """
            ### 선호 여행 스타일을 조회합니다. \n
            styleId를 통해 특정 선호 여행 스타일을 조회할 수 있습니다. \n
            
            ### Response Body
            - city: 도시
            - startDate: 여행 시작 날짜
            - endDate: 여행 종료 날짜
            - preferActivity: 선호하는 활동
            - requirement: 요구사항
            """)
    @GetMapping("/{styleId}")
    public ApiResponse<?> getUserPlanStyle(@PathVariable Long styleId) {

        return ApiResponse.of(SuccessStatus._OK, styleQueryService.getStyle(styleId, SecurityUtils.getCurrentUserId()));
    }

    @Operation(summary = "[선호 여행 스타일 최종 제출]", description = """
            ### 선호 여행 스타일을 최종 제출합니다. \n
            styleId를 통해 특정 선호 여행 스타일을 최종 제출할 수 있습니다. \n
            
            ### Response Body
            - restaurantIds: 추천 식당 ID 리스트
            - attractionIds: 추천 관광지 ID 리스트
            """)
    @PostMapping("/{styleId}/final")
    public ApiResponse<?> saveFinalUserPlanStyle(@PathVariable Long styleId) {
        return ApiResponse.onSuccess(SuccessStatus._OK, styleCommandService.submitStyle(styleId, SecurityUtils.getCurrentUserId()));
    }


}
