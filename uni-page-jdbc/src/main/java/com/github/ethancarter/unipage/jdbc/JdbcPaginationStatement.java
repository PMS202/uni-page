package com.github.ethancarter.unipage.jdbc;

import com.github.ethancarter.unipage.statement.PaginationStatement;
import jakarta.annotation.Nullable;

import java.util.function.Function;


/**
 * jdbc分页语句
 *
 * @author Ethan Carter
 * @date 2023/08/18
 */
public interface JdbcPaginationStatement extends PaginationStatement {

    @Override
    default String getLanguage() {
        return "JDBC";
    }

    /**
     * 返回原生语句
     *
     * @return 原生语句
     */
    String getNativeStatement();

    /**
     * 得到查询参数
     */
    @Nullable
    Object getParameter();

    static JdbcPaginationStatement of(Function<NamedParameterJdbcStatement.Builder,
            NamedParameterJdbcStatement.Builder> function) {
        return NamedParameterJdbcStatement.of(function);
    }

}
