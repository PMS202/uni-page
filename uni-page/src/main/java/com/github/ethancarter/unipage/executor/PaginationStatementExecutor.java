package com.github.ethancarter.unipage.executor;

import com.github.ethancarter.unipage.domain.PageInformation;
import com.github.ethancarter.unipage.exception.PaginationException;
import com.github.ethancarter.unipage.result.set.PaginationResultSet;
import com.github.ethancarter.unipage.statement.PaginationStatement;

/**
 * 分页语句执行程序
 *
 * @author Ethan Carter
 * @date 2023/07/27
 */
public interface PaginationStatementExecutor<Statement extends PaginationStatement> {

    /**
     * 验证是否支持语句的执行
     *
     * @param statement 分页语句
     * @return boolean true 支持
     */
    boolean supports(PaginationStatement statement);

    /**
     * 执行信息
     *
     * @param statement 分页语句
     * @return {@link PageInformation}
     */
    PageInformation executeForInformation(Statement statement) throws PaginationException;

    /**
     * 执行分页查询, 通常是直接操作最底层的API封装后, 返回通用的结果集
     * <p>
     * 例如：JDBC ResultSet
     * <p>
     * 例如：MongoDB Bson
     * <p>
     * 例如：ES Json
     *
     * @param statement 分页语句
     * @return {@link PaginationResultSet}
     */
    PaginationResultSet executeForResultSet(Statement statement) throws PaginationException;

}
