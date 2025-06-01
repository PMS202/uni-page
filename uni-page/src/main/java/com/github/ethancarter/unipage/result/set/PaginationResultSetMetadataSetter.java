package com.github.ethancarter.unipage.result.set;

/**
 * 分页结果集元数据设置器
 *
 * @author Ethan Carter
 * @date 2025/05/30
 */
public interface PaginationResultSetMetadataSetter {

    /**
     * 设置元数据
     *
     * @param metadata 元数据
     */
    void setMetadata(PaginationResultSetMetadata metadata);
}
