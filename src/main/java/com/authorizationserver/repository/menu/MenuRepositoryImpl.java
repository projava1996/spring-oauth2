package com.authorizationserver.repository.menu;

import com.mysql.cj.util.StringUtils;
import com.authorizationserver.dto.base.PaginationResponse;
import com.authorizationserver.dto.menu.MenuDto;
import com.authorizationserver.dto.menu.MenuSearchRequest;
import com.authorizationserver.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
@Slf4j
public class MenuRepositoryImpl implements MenuRepositoryCustom {
    @Autowired
    private  NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PaginationResponse<MenuDto> searchMenu(MenuSearchRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        var parameters = new MapSqlParameterSource();
        stringBuilder.append(" SELECT a.id as id,");
        stringBuilder.append("        a.app as app,");
        stringBuilder.append("        a.name as name,");
        stringBuilder.append("        a.description as description,");
        stringBuilder.append("        a.url as url,");
        stringBuilder.append("        a.icon as icon,");
        stringBuilder.append("        a.order_no as orderNo,");
        stringBuilder.append("        a.parent_id as parentId,");
        stringBuilder.append("        p.name as parentName");
        stringBuilder.append(" FROM adm_menu a");
        stringBuilder.append("  left join adm_menu p on a.parent_id = p.id");
        stringBuilder.append(" WHERE 1 = 1");

        if (!StringUtils.isNullOrEmpty(request.getName())) {
            stringBuilder.append("   AND a.name like :name");
            parameters.addValue("name", "%" + request.getName() + "%");
        }
        if (!StringUtils.isNullOrEmpty(request.getUrl())) {
            stringBuilder.append("   AND a.type = :url");
            parameters.addValue("url", "%" + request.getUrl() + "%");
        }
        if (!StringUtils.isNullOrEmpty(request.getApp())) {
            stringBuilder.append("   AND a.app = :app");
            parameters.addValue("app", request.getApp());
        }
        stringBuilder.append(" order by a.id");
        stringBuilder.append(" limit :offset, :limit");
        parameters.addValue("offset", request.getPage() * request.getPageSize());
        parameters.addValue("limit", request.getPageSize());
        String sql = stringBuilder.toString();

        var result = namedParameterJdbcTemplate.query(sql, parameters, BeanPropertyRowMapper.newInstance(MenuDto.class));
        result = !CommonUtil.isEmpty(result) ? result : Collections.emptyList();
        StringBuilder stringBuilderCount = new StringBuilder();
        var parametersCount = new MapSqlParameterSource();
        stringBuilderCount.append(" select count(*)");
        stringBuilderCount.append(" FROM adm_menu a");
        stringBuilderCount.append(" WHERE 1 = 1");

        if (!StringUtils.isNullOrEmpty(request.getName())) {
            stringBuilderCount.append("   AND a.name like :name");
            parametersCount.addValue("name", "%" + request.getName() + "%");
        }
        if (!StringUtils.isNullOrEmpty(request.getUrl())) {
            stringBuilderCount.append("   AND a.type = :url");
            parametersCount.addValue("url", "%" + request.getUrl() + "%");
        }
        if (!StringUtils.isNullOrEmpty(request.getApp())) {
            stringBuilderCount.append("   AND a.app = :app");
            parametersCount.addValue("app", request.getApp());
        }

        String sqlTotal = stringBuilderCount.toString();
        var totalItemRaw = namedParameterJdbcTemplate.queryForObject(sqlTotal, parametersCount, Long.class);
        long totalItem = totalItemRaw != null ? totalItemRaw : 0;
        PaginationResponse<MenuDto> response = new PaginationResponse<>();
        response.setData(result);
        response.setCount(totalItem);
        response.setTotalPages((totalItem / request.getPageSize()) + 1);
        response.setCurrentPage(request.getPage());
        response.setItemsPerPage(request.getPageSize());
        return response;
    }

    @Override
    public Optional<MenuDto> getDetailPaymentOption(Long id) {
        StringBuilder stringBuilder = new StringBuilder();
        var parameters = new MapSqlParameterSource();
        stringBuilder.append(" SELECT a.id as id,");
        stringBuilder.append("        a.app as app,");
        stringBuilder.append("        a.name as name,");
        stringBuilder.append("        a.description as description,");
        stringBuilder.append("        a.url as url,");
        stringBuilder.append("        a.icon as icon,");
        stringBuilder.append("        a.order_no as orderNo,");
        stringBuilder.append("        a.parent_id as parentId,");
        stringBuilder.append("        p.name as parentName");
        stringBuilder.append(" FROM adm_menu a");
        stringBuilder.append("  left join adm_menu p on a.parent_id = p.id");
        stringBuilder.append(" WHERE a.id = :id");

        parameters.addValue("id", id);
        String sql = stringBuilder.toString();
        try {
            var result = namedParameterJdbcTemplate.queryForObject(sql, parameters, BeanPropertyRowMapper.newInstance(MenuDto.class));
            return Optional.ofNullable(result);
        }  catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<MenuDto> getParents(String app) {
        StringBuilder stringBuilder = new StringBuilder();
        var parameters = new MapSqlParameterSource();
        stringBuilder.append(" SELECT a.id as id,");
        stringBuilder.append("        a.app as app,");
        stringBuilder.append("        a.name as name,");
        stringBuilder.append("        a.description as description,");
        stringBuilder.append("        a.url as url,");
        stringBuilder.append("        a.icon as icon,");
        stringBuilder.append("        a.order_no as orderNo,");
        stringBuilder.append("        a.parent_id as parentId,");
        stringBuilder.append("        p.name as parentName");
        stringBuilder.append(" FROM adm_menu a");
        stringBuilder.append("  left join adm_menu p on a.parent_id = p.id");
        stringBuilder.append(" WHERE a.app = :app");

        parameters.addValue("app", app);
        String sql = stringBuilder.toString();
        try {
            var result = namedParameterJdbcTemplate.query(sql, parameters, BeanPropertyRowMapper.newInstance(MenuDto.class));
            return !CommonUtil.isEmpty(result) ? result : Collections.emptyList();
        }  catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}
