package com.cvgenerator.service.dtoConverters;

import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface EntityToDtoConverter<T,K> {

        T convertToDto(K from);

        default List<T> convertListToDto(List<K> fromList){

            List<T> list = fromList.stream()
                                    .map(this::convertToDto)
                                    .collect(Collectors.toList());

            return list;
        }

}
