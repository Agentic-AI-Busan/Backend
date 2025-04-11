package hyu.erica.v1.capstone.api.exception;


import hyu.erica.v1.capstone.api.ApiResponse;
import hyu.erica.v1.capstone.api.code.status.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * GeneralException 처리
     * @param exception GeneralException
     * @return ApiResponse - GeneralException
     */
    @ExceptionHandler(GeneralException.class)
    public ApiResponse<ErrorStatus> baseExceptionHandle(GeneralException exception) {
        log.warn("BaseException. error message: {}", exception.getMessage());
        return new ApiResponse<>(exception.getStatus());
    }

    /**
     * Exception 처리
     * @param exception Exception
     * @return ApiResponse - INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<ErrorStatus> exceptionHandle(Exception exception) {
        log.error("Exception has occurred. {}", exception);
        return new ApiResponse<>(ErrorStatus._INTERNAL_SERVER_ERROR);
    }


}