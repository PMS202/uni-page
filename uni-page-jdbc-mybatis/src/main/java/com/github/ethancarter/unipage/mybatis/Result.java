package com.github.ethancarter.unipage.mybatis;

import com.github.ethancarter.unipage.result.set.ColumnMetadata;
import com.github.ethancarter.unipage.result.set.PaginationRow;

import java.util.List;

/**
 * 执行结果
 *
 * @author Ethan Carter
 * @date 2024/03/11
 */
class Result {

    private final List<ColumnMetadata> columns;
    private final List<PaginationRow> rows;

    Result(List<ColumnMetadata> columns, List<PaginationRow> rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public List<ColumnMetadata> getColumns() {
        return columns;
    }

    public List<PaginationRow> getRows() {
        return rows;
    }
}
