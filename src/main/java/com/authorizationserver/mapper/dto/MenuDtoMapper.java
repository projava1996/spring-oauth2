package com.authorizationserver.mapper.dto;

import com.authorizationserver.domain.Menu;
import com.authorizationserver.dto.menu.MenuDetail;
import com.authorizationserver.mapper.base.BaseEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface MenuDtoMapper extends BaseEntityMapper<MenuDetail, Menu> {

	MenuDtoMapper INSTANCE = Mappers.getMapper( MenuDtoMapper.class );

    MenuDetail toDto(Menu entity);

    @Mapping(target = "app", expression = "java(stringToEnum(dto.getApp(), com.phungpq.authorizationserver.domain.Menu.App.class))")
    Menu toEntity(MenuDetail dto);

    @Mapping(target = "app", expression = "java(stringToEnum(dto.getApp(), com.phungpq.authorizationserver.domain.Menu.App.class))")
    Menu copyDtoToEntity(MenuDetail dto, @MappingTarget Menu entity);

}
