package hyu.erica.capstone.api.code.status;

import hyu.erica.capstone.api.code.BaseErrorCode;
import hyu.erica.capstone.api.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    //공통 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 내부 오류 발생"),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON4000", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON4001", "인증되지 않은 요청입니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON4002", "접근이 거부되었습니다."),
    _BAD_VALUE_REQUEST(HttpStatus.BAD_REQUEST, "COMMON4003", "올바르지 않은 값을 입력 하셨습니다."),
    _INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COMMON4004", "입력값이 유효하지 않습니다."),
    _EMPTY_JWT(HttpStatus.BAD_REQUEST, "COMMON4005", "JWT 토큰이 비어있습니다."),
    _INVALID_JWT(HttpStatus.BAD_REQUEST, "COMMON4006", "유효하지 않은 JWT token입니다."),
    _EXPIRED_JWT(HttpStatus.BAD_REQUEST, "COMMON4007", "만료된 JWT token입니다."),
    _UNSUPPORTED_JWT(HttpStatus.BAD_REQUEST, "COMMON4008", "지원되지 않는 JWT token입니다."),
    _INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "COMMON4009", "유효하지 않은 리프레시 토큰입니다."),
    _EXPIRED_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "COMMON4010", "만료된 리프레시 토큰입니다."),
    _NULL_VALUE(HttpStatus.BAD_REQUEST, "COMMON4011", "값이 입력되지 않았습니다."),
    _VALUE_RANGE_EXCEEDED(HttpStatus.BAD_REQUEST, "COMMON4012", "값이 지정된 범위를 초과합니다."),
    _TERMS_NOT_AGREED(HttpStatus.FORBIDDEN, "COMMON4013", "이용 약관이 동의되지 않았습니다."),
    _MEMBER_EMAIL_EXIST(HttpStatus.BAD_REQUEST, "COMMON4014", "이미 가입 된 이메일입니다. 다른 로그인 방식을 이용해주세요."),
    _RSA_ERROR(HttpStatus.BAD_REQUEST, "COMMON4015", "RSA 에러가 발생했습니다."),

    //유저 에러
    _USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER4040", "사용자를 찾을 수 없습니다."),
    _INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "USER4001", "비밀번호가 일치하지 않습니다."),
    _DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "USER4002", "이미 가입된 이메일입니다."),

    // 여행 취향 관련
    _STYLE_NOT_FOUND(HttpStatus.NOT_FOUND, "STYLE4040", "입력하신 여행 스타일을 찾을 수 없습니다."),
    _UNAUTHORIZED_USER(HttpStatus.FORBIDDEN, "STYLE4001", "해당 사용자는 권한이 없습니다."),

    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build()
                ;
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
