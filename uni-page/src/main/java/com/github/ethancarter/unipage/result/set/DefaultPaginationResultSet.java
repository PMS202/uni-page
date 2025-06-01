package com.github.ethancarter.unipage.result.set;

import com.github.ethancarter.unipage.util.Assert;

import java.util.Iterator;
import java.util.List;

/**
 * 默认分页结果集
 *
 * @author Ethan Carter
 * @date 2023/08/16
 */
public class DefaultPaginationResultSet extends AbstractPaginationResultSet {

    private final long total;
    private final List<? extends PaginationRow> rows;
    private final PaginationResultSetMetadata metadata;

    public DefaultPaginationResultSet(long total,
                                      PaginationResultSetMetadata metadata,
                                      List<? extends PaginationRow> rows) {
        Assert.state(total > -1, "total Can't be less than zero");
        Assert.notNull(metadata, "PaginationResultSetMetadata must not be null");
        Assert.notNull(rows, "PaginationRowSet must not be null");
        this.total = total;
        this.metadata = metadata;
        for (PaginationRow row : rows) {
            if (row instanceof PaginationResultSetMetadataSetter) {
                ((PaginationResultSetMetadataSetter) row).setMetadata(metadata);
            }
        }
        this.rows = rows;
    }


    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public PaginationResultSetMetadata getMetadata() {
        return metadata;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Iterator<PaginationRow> doIterator() {
        return (Iterator<PaginationRow>) rows.iterator();
    }

    @Override
    protected void doClose() throws Exception {
        rows.clear();
    }
}
