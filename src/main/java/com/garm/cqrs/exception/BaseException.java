package com.garm.cqrs.exception;

import com.garm.cqrs.enums.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private String code;
    private String message;
    private Object[] arguments;
    private ExceptionCode exceptionCode;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(String message, boolean isBaseMessage) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
