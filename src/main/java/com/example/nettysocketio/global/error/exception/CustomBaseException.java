package com.example.nettysocketio.global.error.exception;

import com.example.nettysocketio.global.error.errorCode.ErrorCode;
import lombok.Getter;

/**
 * packageName    : com.example.nettysocketio.global.error.exception
 * fileName       : CustomBaseException
 * author         : Yeong-Huns
 * date           : 2025-02-05
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-05        Yeong-Huns       최초 생성
 */
@Getter
public class CustomBaseException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomBaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomBaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomBaseException(String message) {
        super(message);
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
    }

    public CustomBaseException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public CustomBaseException(ErrorCode errorCode,Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}
