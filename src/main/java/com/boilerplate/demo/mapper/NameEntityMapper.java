package com.boilerplate.demo.mapper;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NameEntityMapper {

    <T> List<T> toEntityList(List<String> names, Class<T> entityClass);
}
