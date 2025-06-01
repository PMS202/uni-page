package com.github.ethancarter.unipage.mybatis.mapper;


import java.lang.reflect.Method;

/**
 * Mapper 语句信息
 *
 * @author Ethan Carter
 * @date 2024/03/11
 */
public interface MapperStatementInfo {
    /**
     * 获取映射器语句 ID
     *
     * @return 字符串
     */
    String getMapperStatementId();

    /**
     * 获取类
     *
     * @return 类
     */
    Class<?> getClasses();

    /**
     * 获取方法名称
     *
     * @return 字符串
     */
    String getMethodName();

    /**
     * 获取 Mapper 语句方法, 为对参数进行匹配
     *
     * @return 方法
     */
    Method getMapperStatementMethod();
}
