package com.boilerplate.demo.mapper.impl;

import com.boilerplate.demo.mapper.NameEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class NameEntityMapperImpl implements NameEntityMapper {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public <T> List<T> toEntityList(List<String> names, Class<T> entityClass) {
        if (names.isEmpty()) {
            return Collections.emptyList(); // return empty list if input list is empty
        }
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.name IN :names";
        return entityManager.createQuery(jpql, entityClass)
                .setParameter("names", names)
                .getResultList();
    }

}

