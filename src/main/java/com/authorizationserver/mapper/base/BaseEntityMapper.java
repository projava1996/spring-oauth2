package com.authorizationserver.mapper.base;

import org.mapstruct.MappingTarget;

import java.util.List;


public interface BaseEntityMapper<D, E> {

	D toDto(E entity);
	E toEntity(D dto);

	List<D> toListDto(List<E> entity);
	List<E> toListEntity(List<D> dto);

	E copyDtoToEntity(D dto, @MappingTarget E entity);

    default <T extends Enum<T>> T stringToEnum(String value, Class<T> enumType) {
        if (value == null) return null;
        return Enum.valueOf(enumType, value.toUpperCase());
    }

    default <T extends Enum<T>> String enumToString(T e) {
        return e == null ? null : e.name();
    }
}
