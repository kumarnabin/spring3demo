package com.boilerplate.demo.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class ExistsColumnValidator implements ConstraintValidator<ExistsColumn, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entityClass;
    private String columnName;

    public void initialize(ExistsColumn constraintAnnotation) {
        entityClass = constraintAnnotation.entityClass();
        columnName = constraintAnnotation.columnName();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String jpql = "select 1 from " + entityClass.getSimpleName() + " where " + columnName + " = :value";
        Optional<Integer> result = entityManager.createQuery(jpql, Integer.class)
                .setParameter("value", value)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();

        return result.isPresent();
    }
}
