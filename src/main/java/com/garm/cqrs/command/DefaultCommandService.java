package com.garm.cqrs.command;

import com.garm.cqrs.domain.AbstractPersistence;
import com.garm.cqrs.exception.BusinessException;
import com.garm.cqrs.exception.EntityNotFoundException;
import com.garm.cqrs.exception.NotSameVersionException;
import com.garm.cqrs.mapper.BaseMapper;
import com.garm.cqrs.model.BaseDto;
import com.garm.cqrs.repository.BaseRepository;
import com.garm.cqrs.utils.TransactionHandler;
import org.springframework.dao.EmptyResultDataAccessException;

import java.io.Serializable;
import java.util.Objects;

public interface DefaultCommandService<E extends AbstractPersistence<L>, D extends BaseDto<L>, L extends Serializable> {

    default BaseRepository<E, L> getRepository() {
        throw new UnsupportedOperationException();
    }

    default BaseMapper<E, D, L> getMapper() {
        throw new UnsupportedOperationException();
    }

    default TransactionHandler getTransactionHandler() {
        throw new UnsupportedOperationException();
    }

    default L create(D dto) {
        return create(dto, false);
    }

    default L create(D dto, boolean runInNewTransaction) {
        return runInNewTransaction
                ? getTransactionHandler().runFunctionInNewTransaction(d -> getRepository().save(getMapper().map(d)).getId(), dto)
                : getRepository().save(getMapper().map(dto)).getId();
    }

    default L createAndFlush(D dto) {
        return getRepository().saveAndFlush(getMapper().map(dto)).getId();
    }

    default void delete(L id) {
        delete(id, false);
    }

    default void delete(L id, boolean runInNewTransaction) {
        try {
            if (runInNewTransaction)
                getTransactionHandler().runConsumerInNewTransaction(i -> getRepository().deleteById(i), id);
            else
                getRepository().deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException("error.entity.not.found", id);
        }
    }

    default void update(D dto) {
        update(dto, false);
    }

    default void update(D dto, boolean runInNewTransaction) {
        beforeUpdate(dto);
        if (runInNewTransaction)
            getTransactionHandler().runConsumerInNewTransaction(d -> getRepository().save(getMapper().map(d)), dto);
        else
            getRepository().save(getMapper().map(dto));
    }

    default void updateAndFlush(D dto) {
        beforeUpdate(dto);
        getRepository().saveAndFlush(getMapper().map(dto));
    }


    default void beforeUpdate(D dto) {
        if (Objects.isNull(dto.getId())) {
            throw new BusinessException("error.entity.id.should.not.null");
        }

        if (!Objects.equals(dto.getVersion(), getRepository().findById(dto.getId()).orElseThrow(
                () -> new EntityNotFoundException("error.entity.not.found", dto.getId())
        ).getVersion())) {
            throw new NotSameVersionException();
        }
    }
}
