package com.github.ethancarter.unipage.jdbc;

import com.github.ethancarter.unipage.statement.BasePaginationStatement;
import com.github.ethancarter.unipage.statement.BasePaginationStatementBuilder;
import com.github.ethancarter.unipage.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 命名参数 JDBC 语句. 用于处理命名参数的 SQL 语句
 * 例如：SELECT * FROM table_name WHERE id = :id
 *
 * @author Ethan Carter
 * @date 2025/05/26
 */
public class NamedParameterJdbcStatement extends BasePaginationStatement implements JdbcPaginationStatement {
    /**
     * sql
     */
    private final String sql;

    /**
     * 参数映射
     */
    private final Map<String, Object> paramMap;

    /**
     * jdbc语句
     *
     * @param builder 生成器
     */
    public NamedParameterJdbcStatement(Builder builder) {
        super(builder.getPageable());
        Assert.state(builder.getSql() != null && !builder.getSql().isEmpty(), "Sql must not be empty!");
        this.sql = builder.getSql();
        this.paramMap = builder.getParamMap();
    }

    public static NamedParameterJdbcStatement of(Function<Builder, Builder> builder) {
        return builder.apply(new Builder()).build();
    }

    public static Builder sql(String sql) {
        return new Builder().sql(sql);
    }

    @Override
    public String getNativeStatement() {
        return sql;
    }

    @Override
    public Map<String, Object> getParameter() {
        return paramMap;
    }

    /**
     * jdbc分页语句生成器
     *
     * @author Ethan Carter
     * @date 2023/07/27
     */
    public static class Builder
            extends BasePaginationStatementBuilder<NamedParameterJdbcStatement, Builder> {
        private String sql;
        private final Map<String, Object> paramMap;

        public Builder() {
            this.paramMap = new HashMap<>();
        }

        public Builder sql(String sql) {
            this.sql = sql;
            return self();
        }

        public Builder paramMap(String key, Object value) {
            if (key != null && !key.isEmpty()) {
                paramMap.put(key, value);
            }
            return self();
        }

        public Builder paramMap(String key, Object value, Object... keyValues) {
            paramMap(key, value);
            if (keyValues != null && keyValues.length > 0) {
                for (int i = 0; i < keyValues.length; i += 2) {
                    Object k = keyValues[i];
                    if (key == null) {
                        continue;
                    }
                    Object v = (i + 1) < keyValues.length ? keyValues[i + 1] : null;
                    paramMap(k.toString(), v);
                }
            }
            return self();
        }

        public Builder paramMap(Map<String, Object> paramMap) {
            if (paramMap != null && !paramMap.isEmpty()) {
                for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                    paramMap(entry.getKey(), entry.getValue());
                }
            }
            return self();
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public NamedParameterJdbcStatement build() {
            return new NamedParameterJdbcStatement(this);
        }

        public String getSql() {
            return sql;
        }

        public Map<String, Object> getParamMap() {
            return paramMap;
        }
    }

}
