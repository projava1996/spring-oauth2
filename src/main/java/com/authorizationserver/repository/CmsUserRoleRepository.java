package com.authorizationserver.repository;

import com.authorizationserver.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsUserRoleRepository extends JpaRepository<Role, Long>, CmsUserRoleRepositoryCustom {

}
