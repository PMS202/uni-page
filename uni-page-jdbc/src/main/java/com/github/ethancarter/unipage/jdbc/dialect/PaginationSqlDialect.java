package com.github.ethancarter.unipage.jdbc.dialect;


import com.github.ethancarter.unipage.jdbc.JdbcPaginationStatement;

import java.util.function.Function;

/**
 * 分页sql方言
 *
 * @author Ethan Carter
 * @date 2023/07/31
 */
public interface PaginationSqlDialect {

    String PAGE_NUMBER_PARAM_NAME = "pageNumber";
    String PAGE_SIZE_PARAM_NAME = "pageSize";

    /**
     * 获取计数方言 SQL
     *
     * @param jdbcStatement jdbc语句
     * @return {@link String}
     */
    String getCountDialectSql(JdbcPaginationStatement jdbcStatement);

    /**
     * 得到sql方言
     *
     * @param jdbcStatement jdbc语句
     * @return {@link String}
     */
    default PageDialectSql getPageDialectSql(JdbcPaginationStatement jdbcStatement) {
        return getPageDialectSql(jdbcStatement, e -> e, e -> e);
    }

    /**
     * 得到sql方言
     *
     * @param jdbcStatement jdbc语句
     * @return {@link String}
     */
    PageDialectSql getPageDialectSql(JdbcPaginationStatement jdbcStatement,
                                     Function<PageDialectSql.ParamPair, PageDialectSql.ParamPair> pagePairFunc,
                                     Function<PageDialectSql.ParamPair, PageDialectSql.ParamPair> sizePairFunc);


}
