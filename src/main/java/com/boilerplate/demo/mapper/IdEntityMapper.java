package com.boilerplate.demo.mapper;

import com.boilerplate.demo.entity.Skill;
import com.boilerplate.demo.request.SkillRequest;
import com.boilerplate.demo.response.SkillResponse;
import jakarta.persistence.Entity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IdEntityMapper {

    <T> T toEntity(Long id, Class<T> entityClass);

    <T> List<T> toEntityList(List<Long> ids, Class<T> entityClass);
}
