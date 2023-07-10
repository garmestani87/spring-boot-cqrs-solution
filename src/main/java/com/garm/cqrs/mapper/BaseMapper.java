package com.garm.cqrs.mapper;


import com.garm.cqrs.domain.AbstractPersistence;
import com.garm.cqrs.model.BaseDto;

import java.io.Serializable;

public interface BaseMapper<E extends AbstractPersistence<L>, D extends BaseDto<L>, L extends Serializable> {
    E map(D dto);

    D map(E entity);
}
