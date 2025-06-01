package com.github.ethancarter.unipage.executor;

import com.github.ethancarter.unipage.domain.Page;
import com.github.ethancarter.unipage.domain.PageInformation;
import com.github.ethancarter.unipage.exception.PaginationException;
import com.github.ethancarter.unipage.result.set.PaginationResultSetHandler;
import com.github.ethancarter.unipage.result.set.PaginationRowMapper;
import com.github.ethancarter.unipage.statement.PaginationStatement;

import java.util.Map;

/**
 * 分页操作
 *
 * @author Ethan Carter
 * @date 2024/05/11
 */
public interface PaginationStatementOperations {

    /**
     * 查询信息
     *
     * @param statement 语句
     * @return 页面信息
     * @throws PaginationException 分页引发的异常
     */
    PageInformation queryForInformation(PaginationStatement statement) throws PaginationException;

    /**
     * 查询映射结果集
     *
     * @param statement 陈述
     * @return 列表
     * @throws PaginationException 分页引发的异常
     */
    Page<Map<String, Object>> queryForMapResultSet(PaginationStatement statement) throws PaginationException;

    /**
     * 查询结果集
     *
     * @param statement 语句
     * @param rowMapper 行映射器
     * @return 列表
     * @throws PaginationException 分页引发的异常
     */
    <T> Page<T> queryForResultSet(PaginationStatement statement,
                                  PaginationRowMapper<T> rowMapper) throws PaginationException;

    /**
     * 查询结果集
     *
     * @param statement 语句
     * @param handler 处理器
     * @return 列表
     * @throws PaginationException 分页引发的异常
     */
    <T> Page<T> queryForResultSet(PaginationStatement statement,
                                  PaginationResultSetHandler<T> handler) throws PaginationException;
}
