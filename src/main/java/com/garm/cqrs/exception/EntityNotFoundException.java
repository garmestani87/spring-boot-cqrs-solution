package com.garm.cqrs.exception;

import com.garm.cqrs.enums.ExceptionCode;

public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(String message, Object... args) {
        super(message);
        setArguments(args);
        setExceptionCode(ExceptionCode.NOT_FOUND);
    }
}
