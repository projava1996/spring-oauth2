package com.authorizationserver.service.security;

import com.authorizationserver.domain.Users;
import com.authorizationserver.dto.cmsUser.CmsUserPrincipal;
import com.authorizationserver.repository.CmsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class JpaCmsUserDetailsManager implements UserDetailsManager {
    private final CmsUserRepository cmsUserRepository;

    @Autowired
    public JpaCmsUserDetailsManager(CmsUserRepository cmsUserRepository) {
        this.cmsUserRepository = cmsUserRepository;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return this.cmsUserRepository.findFirstByLgName(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = cmsUserRepository.findFirstByLgName(username);
        if (user.isPresent()) {
            return new CmsUserPrincipal(user.get());
        }
        throw new UsernameNotFoundException(MessageFormat.format("username {0} not found", username));
    }
}
