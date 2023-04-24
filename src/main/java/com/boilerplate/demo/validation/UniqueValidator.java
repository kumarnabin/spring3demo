package com.boilerplate.demo.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private String[] fields;
    private Class<?> entity;

    @Override
    public void initialize(Unique constraintAnnotation) {
        fields = constraintAnnotation.fields();
        entity = constraintAnnotation.entity();
    }

    @Override
    @Transactional
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // Build query
        StringBuilder queryBuilder = new StringBuilder("select count(*) from ")
                .append(entity.getName()).append(" where ");
        for (String field : fields) {
            Field entityField = ReflectionUtils.findField(entity, field);
            assert entityField != null;
            entityField.setAccessible(true);
            Object fieldValue = ReflectionUtils.getField(entityField, value);
            queryBuilder.append(field).append(" = '").append(fieldValue).append("' and ");
        }
        queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length() - 1);

        // Execute query
        Long result = (Long) entityManager.createNativeQuery(queryBuilder.toString()).getSingleResult();

        // Validate result
        return result == 0L;
    }
}

