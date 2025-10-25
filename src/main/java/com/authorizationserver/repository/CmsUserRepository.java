package com.authorizationserver.repository;

import com.authorizationserver.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CmsUserRepository extends JpaRepository<Users, Long>, CmsUserRepositoryCustom {
    Optional<Users> findFirstByLgName(String lgName);
    Optional<Users> findFirstById(Long id);
}
