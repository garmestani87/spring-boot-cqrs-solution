package com.garm.cqrs.domain;


import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractAuditingTreeEntity<L extends Serializable, E extends AbstractTreePersistence<L, E>> extends AbstractTreePersistence<L, E> {

}

