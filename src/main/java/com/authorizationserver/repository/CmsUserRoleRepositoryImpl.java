package com.authorizationserver.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class CmsUserRoleRepositoryImpl implements CmsUserRoleRepositoryCustom {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

}
