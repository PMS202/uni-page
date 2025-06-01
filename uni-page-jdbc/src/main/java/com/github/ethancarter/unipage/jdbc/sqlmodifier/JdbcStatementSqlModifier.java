package com.github.ethancarter.unipage.jdbc.sqlmodifier;

import com.github.ethancarter.unipage.domain.Sort;
import com.github.ethancarter.unipage.jdbc.JdbcPaginationStatement;
import com.github.ethancarter.unipage.util.Assert;


public class JdbcStatementSqlModifier extends CompositeSqlModifier {

    public JdbcStatementSqlModifier(JdbcPaginationStatement jdbcStatement) {
        Assert.notNull(jdbcStatement, "JdbcStatement must not be null");
        Sort sort = jdbcStatement.getPageable().getSort();
        if (sort != null && sort.isSorted()) {
            addModifier(new SortModifier(sort));
        }
    }
}
