package com.authorizationserver.service.cmsuser;

import com.authorizationserver.domain.Users;
import com.authorizationserver.repository.CmsUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final CmsUserRepository cmsUserRepository;

    @Override
    public Users getUserByLogName(String loginName) {
        return cmsUserRepository.findFirstByLgName(loginName)
                .orElse(null);
    }
}
