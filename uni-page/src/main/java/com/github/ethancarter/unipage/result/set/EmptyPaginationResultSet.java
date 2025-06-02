package com.github.ethancarter.unipage.result.set;

import com.github.ethancarter.unipage.domain.Pageable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 空分页结果集
 *
 * @author Ethan Carter
 * @date 2023/08/14
 */
public class EmptyPaginationResultSet implements PaginationResultSet {
    public final PaginationResultSetMetadata metadata;

    public EmptyPaginationResultSet(Pageable pageable) {
        this(new EmptyPaginationResultSetMetadata(pageable));
    }

    public EmptyPaginationResultSet(PaginationResultSetMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public long getTotal() {
        return 0;
    }

    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public PaginationResultSetMetadata getMetadata() {
        return metadata;
    }


    @Override
    public Iterator<PaginationRow> iterator() {
        return new Iterator<PaginationRow>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public PaginationRow next() {
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public void close() throws Exception {

    }
}