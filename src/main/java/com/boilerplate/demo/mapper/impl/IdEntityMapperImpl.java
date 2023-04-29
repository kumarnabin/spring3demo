package com.boilerplate.demo.mapper.impl;

import com.boilerplate.demo.mapper.IdEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class IdEntityMapperImpl implements IdEntityMapper {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public <T> T toEntity(Long id, Class<T> entityClass) {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.id = :value";
        return entityManager.createQuery(jpql, entityClass)
                .setParameter("value", id)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }



    @Override
    public <T> List<T> toEntityList(List<Long> ids, Class<T> entityClass) {
        if(ids.isEmpty()) {
            return Collections.emptyList(); // return empty list if input list is empty
        }
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.id IN :ids";
        return entityManager.createQuery(jpql, entityClass)
                .setParameter("ids", ids)
                .getResultList();
    }

}

