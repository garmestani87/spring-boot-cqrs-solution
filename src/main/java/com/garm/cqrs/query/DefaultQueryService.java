package com.garm.cqrs.query;


import com.garm.cqrs.domain.AbstractPersistence;
import com.garm.cqrs.exception.EntityNotFoundException;
import com.garm.cqrs.mapper.BaseMapper;
import com.garm.cqrs.model.BaseDto;
import com.garm.cqrs.repository.BaseRepository;

import java.io.Serializable;

public interface DefaultQueryService<E extends AbstractPersistence<L>, D extends BaseDto<L>, L extends Serializable> {

    default BaseRepository<E, L> getRepository() {
        throw new UnsupportedOperationException();
    }

    default BaseMapper<E, D, L> getMapper() {
        throw new UnsupportedOperationException();
    }

    default D findById(L id) {

        return getMapper().map(getRepository().findById(id).orElseThrow(() -> new EntityNotFoundException("error.entity.not.found", id)));
    }

    /*default PagingResponse<List<D>> findPaging(PagingRequest pagingRequest) {

        return (new PaginationExecutor<>(pagingRequest, getRepository(), getMapper())).execute();
    }*/

    // you can read more about findPaging function in below repo
    // #############################################################################################
    // ########## https://github.com/garmestani87/spring-custom-pagination-sort.git ################
    // #############################################################################################
}
