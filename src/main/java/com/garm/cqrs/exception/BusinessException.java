package com.garm.cqrs.exception;

import com.garm.cqrs.enums.ExceptionCode;

public final class BusinessException extends BaseException {

    public BusinessException(String message, Object... args) {
        super(message);
        setArguments(args);
        setExceptionCode(ExceptionCode.BUSINESS_EXCEPTION);
    }
}
