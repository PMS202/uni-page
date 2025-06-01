package com.github.ethancarter.unipage.jdbc.dialect;


import com.github.ethancarter.unipage.jdbc.JdbcPaginationStatement;
import com.github.ethancarter.unipage.util.Assert;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 分页sql自动方言
 *
 * @author Ethan Carter
 * @date 2023/07/31
 */
public class PaginationSqlAutomatedDialect implements PaginationSqlDialect {

    private static final Map<String, Class<? extends PaginationSqlDialect>> dialectAliasMap = new HashMap<>();

    static {
        registerDialectAlias("oracle", OracleSqlDialect.class);
        registerDialectAlias("dm", OracleSqlDialect.class);
        registerDialectAlias("edb", OracleSqlDialect.class);
        registerDialectAlias("mysql", MysqlDialect.class);
        registerDialectAlias("mariadb", MysqlDialect.class);
        registerDialectAlias("sqlite", MysqlDialect.class);
        registerDialectAlias("clickhouse", MysqlDialect.class);
        registerDialectAlias("postgresql", PostgresSqlDialect.class);
    }

    private final DataSource dataSource;

    public PaginationSqlAutomatedDialect(DataSource dataSource) {
        Assert.notNull(dataSource, "DataSource must not be null!");
        this.dataSource = dataSource;
    }

    @Override
    public String getCountDialectSql(JdbcPaginationStatement jdbcStatement) {
        return getDialect(dataSource).getCountDialectSql(jdbcStatement);
    }

    @Override
    public PageDialectSql getPageDialectSql(JdbcPaginationStatement jdbcStatement,
                                            Function<PageDialectSql.ParamPair, PageDialectSql.ParamPair> pageParamPairFunc,
                                            Function<PageDialectSql.ParamPair, PageDialectSql.ParamPair> sizeParamPairFunc) {
        return getDialect(dataSource).getPageDialectSql(jdbcStatement, pageParamPairFunc, sizeParamPairFunc);
    }

    protected PaginationSqlDialect getDialect(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            String url = connection.getMetaData().getURL().toLowerCase();
            for (Map.Entry<String, Class<? extends PaginationSqlDialect>> entry : dialectAliasMap.entrySet()) {
                if (url.contains(":" + entry.getKey() + ":")) {
                    return entry.getValue().getConstructor().newInstance();
                }
            }
            throw new UnsupportedOperationException("Unsupported database type: " + url);
        } catch (SQLException e) {
            throw new PaginationSqlDialectException("Error getting connection from data source.", e);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new PaginationSqlDialectException(e);
        }
    }

    public static void registerDialectAlias(String alias, Class<? extends PaginationSqlDialect> dialectClass) {
        Assert.notEmpty(alias, "Alias must not be empty!");
        Assert.notNull(dialectClass, "Dialect class must not be null!");
        dialectAliasMap.put(alias, dialectClass);
    }
}
