package com.garm.cqrs.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDto<L extends Serializable> implements Serializable {

    private L id;

    private Integer version;
}
