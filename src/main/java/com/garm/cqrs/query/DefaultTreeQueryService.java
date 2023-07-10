package com.garm.cqrs.query;

import com.garm.cqrs.domain.AbstractAuditingTreeEntity;
import com.garm.cqrs.model.BaseTreeDto;

import java.io.Serializable;

public interface DefaultTreeQueryService<E extends AbstractAuditingTreeEntity<L, E>, D extends BaseTreeDto<L, D>, L extends Serializable>
        extends DefaultQueryService<E, D, L> {

}
