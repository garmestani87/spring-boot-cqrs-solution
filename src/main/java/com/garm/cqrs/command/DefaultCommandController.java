package com.garm.cqrs.command;


import com.garm.cqrs.exception.NoSuchServiceExistException;
import com.garm.cqrs.model.BaseDto;
import com.garm.cqrs.model.Response;

import java.io.Serializable;

public interface DefaultCommandController<D extends BaseDto<L>, L extends Serializable> {

    default Response<D, L> create(D dto) {
        throw new NoSuchServiceExistException();
    }

    default Response<L, Void> delete(L id) {
        throw new NoSuchServiceExistException();
    }

    default Response<D, Void> update(D dto) {
        throw new NoSuchServiceExistException();
    }

}

