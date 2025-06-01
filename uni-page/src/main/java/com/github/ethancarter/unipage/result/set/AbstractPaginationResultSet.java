package com.github.ethancarter.unipage.result.set;

import com.github.ethancarter.unipage.exception.PaginationException;

import java.util.Iterator;

/**
 * 抽象分页结果集
 *
 * @author Ethan Carter
 * @date 2024/09/13
 */
public abstract class AbstractPaginationResultSet implements PaginationResultSet {

    private boolean closed;

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void close() throws Exception {
        if (!closed) {
            doClose();
            this.closed = true;
        }
    }

    @Override
    public Iterator<PaginationRow> iterator() {
        if (isClosed()) {
            throw new PaginationException("PaginationResultSet is closed");
        }
        return doIterator();
    }

    protected abstract Iterator<PaginationRow> doIterator();

    protected void doClose() throws Exception {

    }
}
