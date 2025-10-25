package com.authorizationserver.service.cmsuser;


import com.authorizationserver.domain.Users;

public interface UserService {
    Users getUserByLogName(String loginName);
}
