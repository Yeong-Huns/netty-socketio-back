package com.example.nettysocketio.global.error.errorHandler;

import com.example.nettysocketio.global.error.errorCode.ErrorCode;
import com.example.nettysocketio.global.error.errorResponse.ErrorResponse;
import com.example.nettysocketio.global.error.exception.CustomBaseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.example.nettysocketio.global.error.errorHandler
 * fileName       : GlobalExceptionHandler
 * author         : Yeong-Huns
 * date           : 2025-02-05
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-05        Yeong-Huns       최초 생성
 */
@Log4j2
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handle(HttpRequestMethodNotSupportedException e){
        log.error("[HttpRequestMethodNotSupported]: {}, [Detail]: {}", ErrorResponse.fromErrorCode(ErrorCode.METHOD_NOT_ALLOWED), e.getMessage());
        return createErrorResponse(ErrorCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(CustomBaseException.class)
    protected ResponseEntity<ErrorResponse> handle(CustomBaseException e){
        log.error("[CustomBaseException]: {}, [Detail]: {}", ErrorResponse.fromErrorCode(e.getErrorCode()), e.getMessage());
        return createErrorResponse(e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e){
        log.error("[MethodArgumentNotValidException]: {}, [Detail]: {}", ErrorResponse.fromErrorCode(ErrorCode.NOT_VALID_DATA), e.getMessage());
        return createErrorResponse(ErrorCode.NOT_VALID_DATA);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handle(HttpMessageNotReadableException e){
        log.error("[HttpMessageNotReadableException]: {}, [Detail]: {}", ErrorResponse.fromErrorCode(ErrorCode.MESSAGE_NOT_READABLE), e.getMessage());
        return createErrorResponse(ErrorCode.MESSAGE_NOT_READABLE);
    }

    @ExceptionHandler(JsonProcessingException.class)
    protected ResponseEntity<ErrorResponse> handle(JsonProcessingException e){
        log.error("[JsonProcessingException]: {}, [Detail]: {}", ErrorResponse.fromErrorCode(ErrorCode.JSON_PROCESSING_ERROR), e.getMessage());
        return createErrorResponse(ErrorCode.JSON_PROCESSING_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handle(Exception e){
        log.error("[**최종예외처리**]: {}, [Detail]: {}", ErrorResponse.fromErrorCode(ErrorCode.INTERNAL_SERVER_ERROR), e.getMessage());
        return createErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode){
        return new ResponseEntity<ErrorResponse>(
                ErrorResponse.fromErrorCode(errorCode),
                errorCode.getHttpStatus());
    }
}
