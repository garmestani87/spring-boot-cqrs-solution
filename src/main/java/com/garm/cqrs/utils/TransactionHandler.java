package com.garm.cqrs.utils;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class TransactionHandler {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T, R> R runFunctionInNewTransaction(Function<T, R> function, T input) {
        return function.apply(input);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T> void runConsumerInNewTransaction(Consumer<T> consumer, T input) {
        consumer.accept(input);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T, R> R runFunctionInTransaction(Function<T, R> function, T input) {
        return function.apply(input);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> void runConsumerInTransaction(Consumer<T> consumer, T input) {
        consumer.accept(input);
    }
}
