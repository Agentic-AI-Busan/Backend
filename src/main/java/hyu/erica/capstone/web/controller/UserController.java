package hyu.erica.capstone.web.controller;

import hyu.erica.capstone.api.ApiResponse;
import hyu.erica.capstone.api.code.status.SuccessStatus;
import hyu.erica.capstone.security.utils.SecurityUtils;
import hyu.erica.capstone.service.user.UserCommandService;
import hyu.erica.capstone.service.user.UserQueryService;
import hyu.erica.capstone.web.dto.user.request.SignInRequestDTO;
import hyu.erica.capstone.web.dto.user.request.SignUpRequestDTO;
import hyu.erica.capstone.web.dto.user.request.UpdateInfoRequestDTO;
import hyu.erica.capstone.web.dto.user.request.UpdateTripPlanRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자", description = "사용자 관련 API")
@CrossOrigin
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    // 회원 가입
    @Operation(summary = "[회원 관련] 회원 가입", description = """
            ### 회원 가입을 진행합니다.
            
            ### Request Body
            - email: 이메일
            - password: 비밀번호
            - nickname: 닉네임
            - phoneNumber: 전화번호
            - birthDate: 생년월일
            - phoneService: 통신사
            - termsOfService: 서비스 이용 약관 동의 여부
            - privacyPolicy: 개인정보 처리 방침 동의 여부
            - marketingAgreement: 마케팅 정보 수신 동의 여부
            
                  """)
    @PostMapping("/sign-up")
    public ApiResponse<?> signUp(
            @RequestBody SignUpRequestDTO request) {
        return ApiResponse.onSuccess(SuccessStatus._OK, userCommandService.signUp(request));
    }

    @Operation(summary = "[회원 관련] 이메일 중복 확인 ", description = """
            ### 이메일 중복을 확인합니다.
            
            ### Request Body
            - email: 이메일
            """)
    @PostMapping("/check-email")
    public ApiResponse<?> checkEmail(@RequestParam String email) {
        return ApiResponse.onSuccess(SuccessStatus._OK, userCommandService.checkEmail(email));
    }

    // 로그인
    @Operation(summary = "[회원 관련] 로그인", description = """
            ### 로그인을 진행합니다. 로그인 성공 시 access-token을 헤더에 포함하여 반환합니다.
            
            ### Request Body
            - email: 이메일
            - password: 비밀번호
            """)
    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody SignInRequestDTO request, HttpServletResponse response) {
        response.setHeader("Authorization", userCommandService.signIn(request));
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    // 토큰 재발급
//    @Operation(summary = "[회원 관련] 토큰 재발급", title = """
//            ### 토큰을 재발급합니다. refresh-token을 이용하여 access-token을 재발급합니다.
//
//            ### Request Header
//            - RefreshToken: {refresh-token}
//            """)
//    @PostMapping("/reissue-token")
//    public ApiResponse<?> reissueToken() {
//        return null;
//    }


    // 마이페이지
//    @Operation(summary = "[회원 관련] 토큰 재발급", title = """
//            ### 마이페이지를 조회합니다.
//            """)
//    @GetMapping("/my-page")
//    public ApiResponse<?> myPage() {
//        return ApiResponse.onSuccess(SuccessStatus._OK, SecurityUtils.getCurrentUserId());
//    }


    // 프로필 수정
    @Operation(summary = "[회원 관련] 프로필 수정", description = """
            ### 프로필을 수정합니다.
            
            ### Request Body
            - nickname: 닉네임
            - profileImage: 프로필 이미지
            - phoneNumber: 전화번호
            """)
    @PostMapping("/edit")
    public ApiResponse<?> editProfile(
            @RequestBody UpdateInfoRequestDTO request) {
        userCommandService.updateInfo(SecurityUtils.getCurrentUserId(), request);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    // 프로필 조회
    @Operation(summary = "[회원 관련] 프로필 조회", description = """
            ### 프로필을 조회합니다.
            """)
    @GetMapping("/profile")
    public ApiResponse<?> getProfile() {
        return ApiResponse.onSuccess(SuccessStatus._OK, userQueryService.getProfile(SecurityUtils.getCurrentUserId()));
    }

    // 마이페이지 - 내 여행 계획 조회
    @Operation(summary = "[회원 관련] 내 여행 계획 조회", description = """
            ### 내 여행 계획을 조회합니다.
            """)
    @GetMapping("/trip-plans")
    public ApiResponse<?> myTripPlans() {
        return ApiResponse.onSuccess(SuccessStatus._OK, userQueryService.getMyTripPlans(SecurityUtils.getCurrentUserId()));
    }

    // 마이페이지 - 내 여행 계획 제목, 메모 수정
    @Operation(summary = "[회원 관련] 내 여행 계획 제목, 메모 수정", description = """
            ### 내 여행 계획의 제목과 메모를 수정합니다.
            
            ### Request Body
            - title: 제목
            - memo: 메모
            """)
    @PostMapping("/trip-plans/{tripPlanId}/edit")
    public ApiResponse<?> editTripPlan(
            @PathVariable Long tripPlanId,
            @RequestBody UpdateTripPlanRequestDTO request) {
        userCommandService.updateTripPlan(tripPlanId, request);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }




    @Operation(summary = "[회원 관련] 내 여행 계획 삭제", description = """
            ### 내 여행 계획을 삭제합니다.
            """)
    @DeleteMapping("/trip-plans/{tripPlanId}")
    public ApiResponse<?> deleteTripPlan(
            @PathVariable Long tripPlanId) {
        userCommandService.deleteTripPlan(tripPlanId);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

}
