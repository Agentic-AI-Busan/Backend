package hyu.erica.capstone.web.controller;

import hyu.erica.capstone.api.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "사용자 관련 API")
@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    // 회원 가입
    @PostMapping("/sign-up")
    public ApiResponse<?> signUp() {
        return null;
    }

    // 토큰 재발급
    @PostMapping("/reissue-token")
    public ApiResponse<?> reissueToken() {
        return null;
    }

    // 로그아웃
    @PostMapping("/logout")
    public ApiResponse<?> logout() {
        return null;
    }

    // 마이페이지
    @GetMapping("/my-page")
    public ApiResponse<?> myPage() {
        return null;
    }


    // 프로필 수정
    @PostMapping("/edit-profile")
    public ApiResponse<?> editProfile() {
        return null;
    }
}
