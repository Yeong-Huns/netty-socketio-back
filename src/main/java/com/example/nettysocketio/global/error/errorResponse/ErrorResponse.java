package com.example.nettysocketio.global.error.errorResponse;

import com.example.nettysocketio.global.error.errorCode.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * packageName    : com.example.nettysocketio.global.error.errorResponse
 * fileName       : ErrorResponse
 * author         : Yeong-Huns
 * date           : 2025-02-05
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-05        Yeong-Huns       최초 생성
 */
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public static ErrorResponse fromErrorCode(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }
}
