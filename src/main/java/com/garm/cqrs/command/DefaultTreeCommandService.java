package com.garm.cqrs.command;


import com.garm.cqrs.domain.AbstractAuditingTreeEntity;
import com.garm.cqrs.exception.EntityNotFoundException;
import com.garm.cqrs.model.BaseTreeDto;

import java.io.Serializable;
import java.util.Objects;

public interface DefaultTreeCommandService<E extends AbstractAuditingTreeEntity<L, E>,
        D extends BaseTreeDto<L, D>,
        L extends Serializable> extends DefaultCommandService<E, D, L> {

    @Override
    default L create(D dto) {
        E entity = getMapper().map(dto);
        //we should call find by id for parent and set it manually
        if (Objects.nonNull(dto.getParent())) {
            entity.setParent(getRepository().findById(dto.getParent().getId()).orElseThrow(
                    () -> new EntityNotFoundException("error.entity.not.found", dto.getParent().getId())
            ));
        }
        return getRepository().save(entity).getId();
    }

    @Override
    default void update(D dto) {
        DefaultCommandService.super.beforeUpdate(dto);
        E newEntity = getMapper().map(dto);
        E oldEntity = getRepository().findById(dto.getId()).orElseThrow(
                () -> new EntityNotFoundException("error.entity.not.found", dto.getId())
        );
        if (Objects.nonNull(newEntity.getParent()) && (Objects.isNull(oldEntity.getParent()) || !oldEntity.getParent().equals(newEntity.getParent()))) {
            //we should call find by id for parent and set it manually
            newEntity.setParent(getRepository().findById(dto.getParent().getId()).orElseThrow(
                    () -> new EntityNotFoundException("error.entity.not.found", dto.getParent().getId())
            ));
            //because newEntity is not managed then children not loaded
            //so we should load children from oldEntity in order to set their path in InfraTreePathListener
            newEntity.setChildren(oldEntity.getChildren());
        }
        getRepository().save(newEntity);
    }
}
