package com.authorizationserver.repository;

import com.authorizationserver.dto.base.PaginationResponse;
import com.authorizationserver.dto.cmsUser.CmsUserDto;
import com.authorizationserver.dto.query.CmsUserQuery;
import com.authorizationserver.utils.CommonUtil;
import com.authorizationserver.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Slf4j
public class CmsUserRepositoryImpl implements CmsUserRepositoryCustom {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<CmsUserDto> searchCmsUsers(CmsUserQuery cmsUserQuery) {
        StringBuilder stringBuilder = new StringBuilder();
        var parameters = new MapSqlParameterSource();
        stringBuilder.append(" SELECT a.id                          as id,");
        stringBuilder.append("        a.name                        as firstName,");
        stringBuilder.append("        a.lastname                    as lastName,");
        stringBuilder.append("        a.email                       as email,");
        stringBuilder.append("        a.mobile                      as mobile,");
        stringBuilder.append("        a.country                     as country,");
        stringBuilder.append("        a.state                       as state,");
        stringBuilder.append("        a.city                        as city,");
        stringBuilder.append("        a.address                     as address,");
        stringBuilder.append("        a.dob                         as dob,");
        stringBuilder.append("        a.language                    as language,");
        stringBuilder.append("        a.merchantID                  as merchantID,");
        stringBuilder.append("        c.merchantName                as merchantName,");
        stringBuilder.append("        t.name                        as accType,");
        stringBuilder.append("        case");
        stringBuilder.append("            when a.status = '00' then ''");
        stringBuilder.append("            when a.status = '01' then 'Verified'");
        stringBuilder.append("            when a.status = '02' then 'Not-Verified'");
        stringBuilder.append("            when a.status = '03' then 'Suspended'");
        stringBuilder.append("            else 'Verify-Pending' End as status");
        stringBuilder.append(" FROM GLUser a");
        stringBuilder.append("          left join merchantinfo c on a.merchant = c.id");
        stringBuilder.append("          left join usertype t on a.type = t.CODE");
        stringBuilder.append(" WHERE a.type = t.code");
        if (StringUtils.isNotBlank(cmsUserQuery.getName())) {
            stringBuilder.append("   AND (lower(a.name) like :name or lower(a.lastname) like :name)");
            parameters.addValue("name", "%" + cmsUserQuery.getName().trim().toLowerCase() + "%");
        }
        if (StringUtils.isNotBlank(cmsUserQuery.getEmail())) {
            stringBuilder.append("   AND lower(a.email) like :email");
            parameters.addValue("email", "%" + cmsUserQuery.getEmail().trim().toLowerCase() + "%");
        }
        if (StringUtils.isNotBlank(cmsUserQuery.getMerchantName())) {
            stringBuilder.append("   AND c.merchantName like :merchantName");
            parameters.addValue("merchantName", "%" + cmsUserQuery.getMerchantName().trim().toLowerCase() + "%");
        }
        if (StringUtils.isNotBlank(cmsUserQuery.getType())) {
            stringBuilder.append("   AND a.type = :type");
            parameters.addValue("type", cmsUserQuery.getType());
        }
        if (Objects.nonNull(cmsUserQuery.getMerchantID())) {
            stringBuilder.append("   AND a.id = :merchantID");
            parameters.addValue("merchantID", cmsUserQuery.getMerchantID());
        }
        if (cmsUserQuery.getFromDate() != null) {
            stringBuilder.append("   AND a.createdate >= :fromDate");
            var fromDate = DateUtils.getStartTimeOfTheDay(cmsUserQuery.getFromDate());
            parameters.addValue("fromDate", fromDate);
        }
        if (cmsUserQuery.getToDate() != null) {
            stringBuilder.append("   AND a.createdate <= :toDate");
            var toDate = DateUtils.getEndTimeOfTheDay(cmsUserQuery.getToDate());
            parameters.addValue("toDate", toDate);
        }
        if (StringUtils.isNotBlank(cmsUserQuery.getMobile())) {
            stringBuilder.append("   AND a.mobile like :mobile");
            parameters.addValue("mobile", "%" + cmsUserQuery.getMobile().trim() + "%");
        }
        if (StringUtils.isNotBlank(cmsUserQuery.getStatus())) {
            stringBuilder.append("   AND a.status = :status");
            parameters.addValue("status", cmsUserQuery.getStatus());
        }
        stringBuilder.append(" order by a.id");
        stringBuilder.append(" limit :offset, :limit");
        int offset = cmsUserQuery.getCurrentPage() * cmsUserQuery.getPageSize();
        parameters.addValue("offset", offset);
        int limit = cmsUserQuery.getPageSize();
        parameters.addValue("limit", limit);
        String sql = stringBuilder.toString();

        var result = namedParameterJdbcTemplate.query(sql, parameters, BeanPropertyRowMapper.newInstance(CmsUserDto.class));
        result = !CommonUtil.isEmpty(result) ? result : Collections.emptyList();
        StringBuilder stringBuilderCount = new StringBuilder();
        var parametersCount = new MapSqlParameterSource();
        stringBuilderCount.append(" SELECT count(*)");
        stringBuilderCount.append(" FROM GLUser a");
        stringBuilderCount.append("          left join merchantinfo c on a.merchant = c.id");
        stringBuilderCount.append("          left join usertype t on a.type = t.CODE");
        stringBuilderCount.append(" WHERE a.type = t.code");
        if (StringUtils.isNotBlank(cmsUserQuery.getName())) {
            stringBuilderCount.append("   AND (lower(a.name) like :name or lower(a.lastname) like :name)");
            parametersCount.addValue("name", "%" + cmsUserQuery.getName().trim().toLowerCase() + "%");
        }
        if (StringUtils.isNotBlank(cmsUserQuery.getEmail())) {
            stringBuilderCount.append("   AND lower(a.email) like :email");
            parametersCount.addValue("email", "%" + cmsUserQuery.getEmail().trim().toLowerCase() + "%");
        }
        if (StringUtils.isNotBlank(cmsUserQuery.getMerchantName())) {
            stringBuilderCount.append("   AND c.merchantName like :merchantName");
            parametersCount.addValue("merchantName", "%" + cmsUserQuery.getMerchantName().trim().toLowerCase() + "%");
        }
        if (StringUtils.isNotBlank(cmsUserQuery.getType())) {
            stringBuilderCount.append("   AND a.type = :type");
            parametersCount.addValue("type", cmsUserQuery.getType());
        }
        if (Objects.nonNull(cmsUserQuery.getMerchantID())) {
            stringBuilderCount.append("   AND a.id = :merchantID");
            parametersCount.addValue("merchantID", cmsUserQuery.getMerchantID());
        }
        if (cmsUserQuery.getFromDate() != null) {
            stringBuilderCount.append("   AND a.createdate >= :fromDate");
            var fromDate = DateUtils.getStartTimeOfTheDay(cmsUserQuery.getFromDate());
            parametersCount.addValue("fromDate", fromDate);
        }
        if (cmsUserQuery.getToDate() != null) {
            stringBuilderCount.append("   AND a.createdate <= :toDate");
            var toDate = DateUtils.getEndTimeOfTheDay(cmsUserQuery.getToDate());
            parametersCount.addValue("toDate", toDate);
        }
        if (StringUtils.isNotBlank(cmsUserQuery.getMobile())) {
            stringBuilderCount.append("   AND a.mobile like :mobile");
            parametersCount.addValue("mobile", "%" + cmsUserQuery.getMobile().trim() + "%");
        }
        if (StringUtils.isNotBlank(cmsUserQuery.getStatus())) {
            stringBuilderCount.append("   AND a.status = :status");
            parametersCount.addValue("status", cmsUserQuery.getStatus());
        }
//
        String sqlTotal = stringBuilderCount.toString();
        var totalItemRaw = namedParameterJdbcTemplate.queryForObject(sqlTotal, parametersCount,Long.class);
        long totalItem = totalItemRaw != null ? totalItemRaw : 0;
        PaginationResponse<CmsUserDto> response = new PaginationResponse<>();
        response.setData(result);
        response.setCount(totalItem);
        response.setTotalPages((totalItem / cmsUserQuery.getPageSize()) + 1);
        response.setCurrentPage(cmsUserQuery.getCurrentPage());
        response.setItemsPerPage(cmsUserQuery.getPageSize());
        return response;
    }

    @Override
    public Optional<CmsUserDto> getCmsUserDetail(Long id) {
        StringBuilder stringBuilder = new StringBuilder();
        var parameters = new MapSqlParameterSource();
        stringBuilder.append(" SELECT a.id                          as id,");
        stringBuilder.append("        a.name                        as firstName,");
        stringBuilder.append("        a.lastname                    as lastName,");
        stringBuilder.append("        a.email                       as email,");
        stringBuilder.append("        a.mobile                      as mobile,");
        stringBuilder.append("        a.state                       as state,");
        stringBuilder.append("        a.country                     as country,");
        stringBuilder.append("        a.city                        as city,");
        stringBuilder.append("        a.address                     as address,");
        stringBuilder.append("        a.merchantID                  as merchantID,");
        stringBuilder.append("        a.dob                         as dob,");
        stringBuilder.append("        a.language                    as language,");
        stringBuilder.append("        c.merchantName                as merchantName,");
        stringBuilder.append("        t.name                        as accType,");
        stringBuilder.append("        case");
        stringBuilder.append("            when a.status = '00' then ''");
        stringBuilder.append("            when a.status = '01' then 'Verified'");
        stringBuilder.append("            when a.status = '02' then 'Not-Verified'");
        stringBuilder.append("            when a.status = '03' then 'Suspended'");
        stringBuilder.append("            else 'Verify-Pending' End as status");
        stringBuilder.append(" FROM GLUser a");
        stringBuilder.append("          left join merchantinfo c on a.merchant = c.id");
        stringBuilder.append("          left join usertype t on a.type = t.CODE");
        stringBuilder.append(" WHERE a.id = :id");
        parameters.addValue("id", id);
        String sql = stringBuilder.toString();
        try {
            var result = namedParameterJdbcTemplate.queryForObject(sql, parameters, BeanPropertyRowMapper.newInstance(CmsUserDto.class));
            return Optional.ofNullable(result);
        }   catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findUserAuthorityByUsername(String lgName) {
        StringBuilder stringBuilder = new StringBuilder();
        var parameters = new MapSqlParameterSource();
        stringBuilder.append(" select r.name as name from cmsrole r");
        stringBuilder.append("         left join cmsroleuser ru on r.roleId = ru.roleId");
        stringBuilder.append("         left join cmsuser u on ru.userId = u.userId");
        stringBuilder.append(" where u.lgname = :lgName");

        parameters.addValue("lgName", lgName);
        String sql = stringBuilder.toString();
        var result = namedParameterJdbcTemplate.queryForList(sql, parameters, String.class);
        result = !CommonUtil.isEmpty(result) ? result : Collections.emptyList();
        return result;
    }

    @Override
    public List<String> findUserPermissionByUsername(String lgName) {
        StringBuilder stringBuilder = new StringBuilder();
        var parameters = new MapSqlParameterSource();
        stringBuilder.append(" select p.name from cms_permission p");
        stringBuilder.append("          left join cmsrole_permission rp on p.id = rp.permission_id");
        stringBuilder.append("          left join cmsrole r on rp.role_id = r.roleId");
        stringBuilder.append("         left join cmsroleuser ru on r.roleId = ru.roleId");
        stringBuilder.append("         left join cmsuser u on ru.userId = u.userId");
        stringBuilder.append(" where u.lgname = :lgName");
        parameters.addValue("lgName", lgName);
        String sql = stringBuilder.toString();
        var result = namedParameterJdbcTemplate.queryForList(sql, parameters, String.class);
        result = !CommonUtil.isEmpty(result) ? result : Collections.emptyList();
        return result;
    }
}
