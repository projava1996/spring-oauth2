package com.authorizationserver.controller;

import com.authorizationserver.domain.Users;
import com.authorizationserver.service.cmsuser.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@AllArgsConstructor
public abstract class BaseController {
    private final UserService userService;

    protected Users getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return userService.getUserByLogName(authentication.getName());
    }
}
