package com.garm.cqrs.exception;

import com.garm.cqrs.enums.ExceptionCode;

public final class NotSameVersionException extends BaseException {

    public NotSameVersionException(Object... args) {
        super("error.business.audit.version");
        setExceptionCode(ExceptionCode.NOT_SAME_VERSION);
        setArguments(args);
    }
}
