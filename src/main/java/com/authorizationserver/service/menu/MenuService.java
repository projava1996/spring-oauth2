package com.authorizationserver.service.menu;


import com.authorizationserver.domain.Users;
import com.authorizationserver.dto.base.HttpResponse;
import com.authorizationserver.dto.base.PaginationResponse;
import com.authorizationserver.dto.menu.MenuCreateRequest;
import com.authorizationserver.dto.menu.MenuDetail;
import com.authorizationserver.dto.menu.MenuDto;
import com.authorizationserver.dto.menu.MenuSearchRequest;

import java.util.List;

public interface MenuService {
    PaginationResponse<MenuDto> searchMenu(MenuSearchRequest query);
    MenuDto getMenuDetail(Long id);
    List<MenuDto> getParents(String app);

    MenuDetail createMenu(MenuCreateRequest request, Users user);
    MenuDetail editMenu(Long id, MenuCreateRequest request);
    HttpResponse deleteMenu(Long id);

}
