package com.github.ethancarter.unipage.mybatis.parameter;

import com.github.ethancarter.unipage.mybatis.mapper.MapperStatementInfo;

/**
 * 参数名称解析器供应商
 *
 * @author Ethan Carter
 * @date 2024/03/29
 */
public interface ParamNameResolverSupplier {
    /**
     * 获取
     *
     * @param info     语句信息
     * @param parameters 参数
     * @return 参数
     */
    Object get(MapperStatementInfo info, Object[] parameters);
}
