package com.github.ethancarter.unipage.jdbc.sql2o;

import com.github.ethancarter.unipage.jdbc.ResultSetMetadataColumnFactory;
import com.github.ethancarter.unipage.result.set.ColumnMetadata;
import com.github.ethancarter.unipage.result.set.PaginationRow;
import org.sql2o.ResultSetHandler;
import org.sql2o.ResultSetHandlerFactory;
import org.sql2o.Sql2o;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class Sql2oPaginationResultSetHandlerFactory implements ResultSetHandlerFactory<PaginationRow> {

    private final Sql2o sql2o;
    private List<ColumnMetadata> columns;

    public Sql2oPaginationResultSetHandlerFactory(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public ResultSetHandler<PaginationRow> newResultSetHandler(ResultSetMetaData rsmd) throws SQLException {
        this.columns = ResultSetMetadataColumnFactory.createColumns(rsmd);
        return new Sql2oPaginationResultSetHandler(rsmd, sql2o.isDefaultCaseSensitive(), sql2o.getQuirks());
    }

    public List<ColumnMetadata> getColumns() {
        return columns;
    }
}
