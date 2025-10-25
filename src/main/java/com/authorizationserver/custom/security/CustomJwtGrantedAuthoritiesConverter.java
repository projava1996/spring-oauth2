package com.authorizationserver.custom.security;

import com.authorizationserver.repository.CmsUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;

public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    private final CmsUserRepository cmsUserRepository;

    public CustomJwtGrantedAuthoritiesConverter(CmsUserRepository userRepository) {
        this.cmsUserRepository = userRepository;
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(StringUtils.isNotBlank(source.getSubject())){
          cmsUserRepository.findUserPermissionByUsername(source.getSubject())
                  .forEach(item -> grantedAuthorities.add(new SimpleGrantedAuthority(item)));
        }
        return grantedAuthorities;
    }
}
