package com.garm.cqrs.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BaseTreeDto<L extends Serializable, T extends BaseTreeDto<L, T>> extends BaseDto<L> {

    protected T parent;

    protected List<T> children = new ArrayList<>();

    protected String path;
}
