package com.garm.cqrs.query;


import com.garm.cqrs.model.BaseDto;
import com.garm.cqrs.model.Response;

import java.io.Serializable;

public interface DefaultQueryController<D extends BaseDto<L>, L extends Serializable> {

    default Response<L, D> findById(L id) {
        return null;
    }

    /*default Response<PagingRequest, PagingResponse> findPaging(PagingRequest pagingRequest) {
        return null;
    }*/

    // you can read more about findPaging function in below repo
    // #############################################################################################
    // ########## https://github.com/garmestani87/spring-custom-pagination-sort.git ################
    // #############################################################################################


}
