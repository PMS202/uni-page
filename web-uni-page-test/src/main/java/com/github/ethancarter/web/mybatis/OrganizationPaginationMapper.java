package com.github.ethancarter.web.mybatis;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrganizationPaginationMapper {

    List<Map<String, Object>> getOrganizations(String name);
}
