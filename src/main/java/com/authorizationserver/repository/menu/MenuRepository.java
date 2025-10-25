package com.authorizationserver.repository.menu;

import com.authorizationserver.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>, MenuRepositoryCustom{

}
