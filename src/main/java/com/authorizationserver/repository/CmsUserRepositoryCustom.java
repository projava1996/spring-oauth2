package com.authorizationserver.repository;


import com.authorizationserver.dto.base.PaginationResponse;
import com.authorizationserver.dto.cmsUser.CmsUserDto;
import com.authorizationserver.dto.query.CmsUserQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CmsUserRepositoryCustom {
    PaginationResponse<CmsUserDto> searchCmsUsers(CmsUserQuery cmsUserQuery);
    Optional<CmsUserDto> getCmsUserDetail(Long id);

    List<String> findUserAuthorityByUsername(String lgName);
    List<String> findUserPermissionByUsername(String lgName);
}
