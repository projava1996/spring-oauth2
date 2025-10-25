package com.authorizationserver.controller.menu;

import com.authorizationserver.contants.Permissions;
import com.authorizationserver.controller.BaseController;
import com.authorizationserver.dto.menu.MenuCreateRequest;
import com.authorizationserver.dto.menu.MenuSearchRequest;
import com.authorizationserver.service.cmsuser.UserService;
import com.authorizationserver.service.menu.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/system/menu")
public class MenuController extends BaseController {
    private final MenuService menuService;

    public MenuController(UserService userService, MenuService menuService) {
        super(userService);
        this.menuService = menuService;
    }
    @GetMapping("/parents")
    @PreAuthorize("hasAuthority('" + Permissions.ADMIN_PAYMENT_OPTION_SEARCH + "')")
    public ResponseEntity<Object> getParents( @RequestParam(required = false, defaultValue = "") String app) {
        var response = menuService.getParents(app);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/search")
    @PreAuthorize("hasAuthority('" + Permissions.ADMIN_MENU_VIEW + "')")
    public ResponseEntity<Object> searchVirtualCard(@RequestBody @Valid MenuSearchRequest query) {
        var response = menuService.searchMenu(query);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('" + Permissions.ADMIN_VIRTUAL_CARD_CREATE_FUND_POST + "')")
    public ResponseEntity<Object> createNewMenu(@RequestBody @Valid MenuCreateRequest query) {
        var response = menuService.createMenu(query, getUser());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + Permissions.ADMIN_PAYMENT_OPTION_SEARCH + "')")
    public ResponseEntity<Object> getMenuDetail(@PathVariable Long id) {
        var response = menuService.getMenuDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + Permissions.ADMIN_PAYMENT_OPTION_SEARCH + "')")
    public ResponseEntity<Object> getPaymentOptionDetail(@PathVariable Long id,
                                                          @RequestBody MenuCreateRequest request) {
        var response = menuService.editMenu(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + Permissions.ADMIN_PAYMENT_OPTION_SEARCH + "')")
    public ResponseEntity<Object> deleteMenuDetail(@PathVariable Long id) {
        var response = menuService.deleteMenu(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
