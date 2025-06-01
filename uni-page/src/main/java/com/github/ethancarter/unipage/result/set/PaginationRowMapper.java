package com.github.ethancarter.unipage.result.set;

import com.github.ethancarter.unipage.exception.PaginationException;
import jakarta.annotation.Nullable;

/**
 * 分页行映射器
 *
 * @author Ethan Carter
 * @date 2024/08/05
 */
public interface PaginationRowMapper<T> {

    @Nullable
    T mapRow(PaginationResultSetMetadata metadata, PaginationRow prs, int rowNum) throws PaginationException;

}
