package com.github.ethancarter.unipage.result.set;

import com.github.ethancarter.unipage.domain.Pageable;

/**
 * 分页结果集元数据
 *
 * @author Ethan Carter
 * @date 2023/08/14
 */
public interface PaginationResultSetMetadata {

    /**
     * 获得可分页
     *
     */
    Pageable getPageable();

    /**
     * 获得列数
     *
     * @return int
     */
    int getColumnCount();

    /**
     * 获得列元数据
     *
     */
    ColumnMetadata getColumnMetadata(int columnIndex);

    /**
     * 获取列元数据
     *
     * @param columnName 列名称
     */
    ColumnMetadata getColumnMetadata(String columnName);
}
