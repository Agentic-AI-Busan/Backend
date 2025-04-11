package hyu.erica.v1.capstone.security.utils;

import hyu.erica.v1.capstone.api.code.status.ErrorStatus;
import hyu.erica.v1.capstone.api.exception.GeneralException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    // 현재 인증된 사용자의 ID를 반환
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new GeneralException(ErrorStatus._UNAUTHORIZED);
        }
        return Long.valueOf(authentication.getName());
    }

//    // 현재 인증된 사용자의 ID와 매개변수로 전달된 ID가 일치하는지 확인
//    public static void verifyUserId(Long memberId) {
//        Long currentUserId = getCurrentUserId();
//        if (!Objects.equals(currentUserId, memberId)) {
//            throw new GeneralException(ErrorStatus._MEMBER_NO_ACCESS);
//        }
//    }

}
