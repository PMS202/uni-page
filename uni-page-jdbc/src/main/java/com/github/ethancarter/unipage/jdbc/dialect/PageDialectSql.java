package com.github.ethancarter.unipage.jdbc.dialect;

/**
 * 分页方言sql
 *
 * @author Ethan Carter
 * @date 2023/08/21
 */
public class PageDialectSql {
    /**
     * sql
     */
    private final String sql;
    /**
     * 页码
     */
    private final ParamPair page;
    /**
     * 页大小
     */
    private final ParamPair size;

    public PageDialectSql(String sql, ParamPair page, ParamPair size) {
        this.sql = sql;
        this.page = page;
        this.size = size;
    }

    public String getSql() {
        return sql;
    }

    public ParamPair getPage() {
        return page;
    }

    public ParamPair getSize() {
        return size;
    }

    /**
     * 参数对
     *
     * @author Ethan Carter
     * @date 2023/08/21
     */
    public static class ParamPair {
        /**
         * 参数名称
         */
        private final String name;
        /**
         * 参数值
         */
        private final long value;

        public ParamPair(String name, long value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public long getValue() {
            return value;
        }
    }
}
