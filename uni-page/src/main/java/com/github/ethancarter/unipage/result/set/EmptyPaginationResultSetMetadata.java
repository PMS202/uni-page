package com.github.ethancarter.unipage.result.set;

import com.github.ethancarter.unipage.domain.Pageable;

public class EmptyPaginationResultSetMetadata implements PaginationResultSetMetadata {
    private final Pageable pageable;

    public EmptyPaginationResultSetMetadata(Pageable pageable) {
        this.pageable = pageable;
    }

    @Override
    public Pageable getPageable() {
        return pageable;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public ColumnMetadata getColumnMetadata(int columnIndex) {
        throw new IllegalArgumentException("No column metadata of " + columnIndex);
    }

    @Override
    public ColumnMetadata getColumnMetadata(String columnName) {
        throw new IllegalArgumentException("No column metadata of " + columnName);
    }
}
