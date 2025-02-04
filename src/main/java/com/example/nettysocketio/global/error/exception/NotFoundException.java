package com.example.nettysocketio.global.error.exception;

import com.example.nettysocketio.global.error.errorCode.ErrorCode;

/**
 * packageName    : com.example.nettysocketio.global.error.exception
 * fileName       : NotFoundException
 * author         : Yeong-Huns
 * date           : 2025-02-05
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-05        Yeong-Huns       최초 생성
 */
public class NotFoundException extends CustomBaseException {
    public NotFoundException(ErrorCode errorCode){
        super(errorCode.getMessage(), errorCode);
    }
    public NotFoundException(){
        super(ErrorCode.NOT_FOUND);
    }
    public NotFoundException(String message){
        super(message, ErrorCode.NOT_FOUND);
    }
}
