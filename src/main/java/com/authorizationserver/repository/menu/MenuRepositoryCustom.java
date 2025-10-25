package com.authorizationserver.repository.menu;


import com.authorizationserver.dto.base.PaginationResponse;
import com.authorizationserver.dto.menu.MenuDto;
import com.authorizationserver.dto.menu.MenuSearchRequest;

import java.util.List;
import java.util.Optional;

public interface MenuRepositoryCustom {

    PaginationResponse<MenuDto> searchMenu(MenuSearchRequest query);
    Optional<MenuDto> getDetailPaymentOption(Long id);

    List<MenuDto> getParents(String app);
}
