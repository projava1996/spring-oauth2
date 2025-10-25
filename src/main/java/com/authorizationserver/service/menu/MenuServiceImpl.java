package com.authorizationserver.service.menu;

import com.authorizationserver.contants.enums.MenuAppEnum;
import com.authorizationserver.domain.Menu;
import com.authorizationserver.domain.Users;
import com.authorizationserver.dto.base.HttpResponse;
import com.authorizationserver.dto.base.PaginationResponse;
import com.authorizationserver.dto.menu.MenuCreateRequest;
import com.authorizationserver.dto.menu.MenuDetail;
import com.authorizationserver.dto.menu.MenuDto;
import com.authorizationserver.dto.menu.MenuSearchRequest;
import com.authorizationserver.exception.ErrorCodeType;
import com.authorizationserver.exception.RestBusinessException;
import com.authorizationserver.mapper.dto.MenuDtoMapper;
import com.authorizationserver.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<MenuDto> searchMenu(MenuSearchRequest query) {
        validateSearchMenu(query);
        return menuRepository.searchMenu(query);
    }

    @Override
    @Transactional(readOnly = true)
    public MenuDto getMenuDetail(Long id) {
        return menuRepository.getDetailPaymentOption(id)
                .orElseThrow(() -> new RestBusinessException(ErrorCodeType.MENU_APP_NOT_FOUND, List.of(String.valueOf(id))));
    }

    @Override
    public List<MenuDto> getParents(String app) {
        if (StringUtils.isNotBlank(app)) {
            return menuRepository.getParents(app);
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public MenuDetail createMenu(MenuCreateRequest form, Users user) {
        validateCreateMenu(form);
        var menu = new Menu();
        menu.setName(form.getName());
        menu.setApp(Menu.App.valueOf(form.getApp()));
        menu.setDescription(form.getDescription());
        menu.setUrl(form.getUrl());
        menu.setOrderNo(form.getOrderNo());
        menu.setIcon(form.getIcon());
        menu.setDisable(form.getDisable());
        menu.setAnonymous(form.getAnonymous());
        menuRepository.save(menu);
        return MenuDtoMapper.INSTANCE.toDto(menu);
    }

    @Override
    @Transactional
    public MenuDetail editMenu(Long id, MenuCreateRequest request) {
        validateCreateMenu(request);
        var menuExist = menuRepository.findById(id)
                .orElseThrow(() -> new RestBusinessException(ErrorCodeType.MENU_APP_NOT_FOUND, List.of(String.valueOf(id))));
        if (request.getParent() != null && request.getParent() != 0) {
            var parent = menuRepository.findById(request.getParent())
                    .orElseThrow(() -> new RestBusinessException(ErrorCodeType.MENU_APP_PARENT_NOT_FOUND, List.of(String.valueOf(id))));
            menuExist.setParent(parent);
        }
        menuExist.setName(request.getName());
        menuExist.setApp(Menu.App.valueOf(request.getApp()));
        menuExist.setDescription(request.getDescription());
        menuExist.setUrl(request.getUrl());
        menuExist.setOrderNo(request.getOrderNo());
        menuExist.setIcon(request.getIcon());
        menuExist.setDisable(request.getDisable());
        menuExist.setAnonymous(request.getAnonymous());
        menuRepository.save(menuExist);
        return MenuDtoMapper.INSTANCE.toDto(menuExist);
    }

    @Override
    @Transactional
    public HttpResponse deleteMenu(Long id) {
        var menuExist = menuRepository.findById(id)
                .orElseThrow(() -> new RestBusinessException(ErrorCodeType.MENU_APP_NOT_FOUND, List.of(String.valueOf(id))));
        menuRepository.delete(menuExist);
        return new HttpResponse(200, HttpStatus.OK, "Delete menu successful!");
    }

    private void validateCreateMenu(MenuCreateRequest request) {
        if (StringUtils.isNotBlank(request.getApp())) {
            var validPaymentType = MenuAppEnum.contains(request.getApp());
            if (!validPaymentType) {
                throw new RestBusinessException(ErrorCodeType.MENU_APP_INVALID, List.of(request.getApp()));
            }
        }
    }

    private void validateSearchMenu(MenuSearchRequest request) {
        if (StringUtils.isNotBlank(request.getApp())) {
            var validPaymentType = MenuAppEnum.contains(request.getApp());
            if (!validPaymentType) {
                throw new RestBusinessException(ErrorCodeType.MENU_APP_INVALID, List.of(request.getApp()));
            }
        }
    }

}
