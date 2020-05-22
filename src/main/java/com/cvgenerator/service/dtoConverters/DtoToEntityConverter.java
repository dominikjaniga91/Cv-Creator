package com.cvgenerator.service.dtoConverters;


@FunctionalInterface
public interface DtoToEntityConverter<T,K> {

        T convertToEntity(K from);
}
