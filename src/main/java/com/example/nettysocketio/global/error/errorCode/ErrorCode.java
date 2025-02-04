package com.example.nettysocketio.global.error.errorCode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.example.nettysocketio.global.error.errorCode
 * fileName       : ErrorCode
 * author         : Yeong-Huns
 * date           : 2025-02-05
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-05        Yeong-Huns       최초 생성
 */
@Getter
public enum ErrorCode {
    ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 사용자입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "대상 리소스를 찾을 수 없습니다."),
    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "지정한 리소스에 대한 액세스 권한이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않는 메소드입니다."),
    NOT_VALID_DATA(HttpStatus.BAD_REQUEST, "Validation 실패."),
    MESSAGE_NOT_READABLE(HttpStatus.BAD_REQUEST, "요청 메시지의 형식이 올바르지 않습니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "요청하신 리소스를 찾을 수 없습니다."),
    JSON_PROCESSING_ERROR(HttpStatus.BAD_REQUEST, "JSON 처리 중 오류가 발생했습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
