package com.garm.cqrs.exception;

import com.garm.cqrs.enums.ExceptionCode;

public class NoSuchServiceExistException extends BaseException {

    public NoSuchServiceExistException() {
        super("error.rest.notSupported");
        setExceptionCode(ExceptionCode.METHOD_NOT_SUPPORTED);
    }
}
