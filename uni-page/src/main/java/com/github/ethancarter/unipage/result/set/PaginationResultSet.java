package com.github.ethancarter.unipage.result.set;


/**
 * 分页结果集
 *
 * @author Ethan Carter
 * @date 2023/07/28
 */
public interface PaginationResultSet extends Iterable<PaginationRow>, AutoCloseable {

    /**
     * 获取数据总数
     *
     * @return long
     */
    long getTotal();

    /**
     * 已关闭
     *
     * @return boolean
     */
    boolean isClosed();

    /**
     * 得到元数据
     *
     * @return {@link PaginationResultSetMetadata}
     */
    PaginationResultSetMetadata getMetadata();

}
