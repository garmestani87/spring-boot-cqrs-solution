package com.garm.cqrs.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;


public enum ExceptionCode implements Convertible<String> {

    NOT_FOUND("not found", 1L),
    BUSINESS_EXCEPTION("business exception", 9L),
    METHOD_NOT_SUPPORTED("method not supported", 10L),
    NOT_SAME_VERSION("not same version", 6L);

    private String value;
    private Long code;

    ExceptionCode(String value, Long code) {
        this.value = value;
        this.code = code;
    }

    public ExceptionCode findByValue(String value) {
        return Arrays.stream(values()).filter(exceptionCode -> exceptionCode.value.equals(value)).findFirst().get();
    }

    @Override
    public String getValue() {
        return value;
    }

    public Long getCode() {
        return code;
    }

    @JsonValue
    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return String.format("code: %d - [%s]", this.code, this.value);
    }
}