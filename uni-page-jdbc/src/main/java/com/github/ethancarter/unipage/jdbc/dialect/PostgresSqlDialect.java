package com.github.ethancarter.unipage.jdbc.dialect;

import com.github.ethancarter.unipage.domain.Pageable;
import com.github.ethancarter.unipage.jdbc.JdbcPaginationStatement;
import net.sf.jsqlparser.JSQLParserException;

import java.util.function.Function;

public class PostgresSqlDialect extends AbstractPaginationSqlDialect {

    @Override
    public PageDialectSql getPageDialectSql(JdbcPaginationStatement jdbcStatement,
                                            Function<PageDialectSql.ParamPair, PageDialectSql.ParamPair> pagePairFunc,
                                            Function<PageDialectSql.ParamPair, PageDialectSql.ParamPair> sizePairFunc) {
        String originalSql = jdbcStatement.getNativeStatement();
        Pageable pageable = jdbcStatement.getPageable();

        // 分页参数
        PageDialectSql.ParamPair pagePair =
                pagePairFunc.apply(new PageDialectSql.ParamPair(PAGE_NUMBER_PARAM_NAME, pageable.getOffset()));
        PageDialectSql.ParamPair sizePair = sizePairFunc
                .apply(new PageDialectSql.ParamPair(PAGE_SIZE_PARAM_NAME, pageable.getPageSize()));

        String modifySql;
        try {
            modifySql = getModifySql(jdbcStatement);
        } catch (JSQLParserException e) {
            logger.warn("Parsing sql [{}] exception", originalSql, e);
            return new PageDialectSql(originalSql, pagePair, sizePair);
        }
        // 分页SQL
        StringBuilder sqlBuilder = new StringBuilder(modifySql.length() + 14);
        sqlBuilder.append(modifySql);

        if (pageable.getPageNumber() == 0) {
            sqlBuilder.append("\n LIMIT :").append(sizePair.getName());
        } else {
            sqlBuilder.append("\n LIMIT :").append(sizePair.getName()).append(" OFFSET :").append(pagePair.getName());
        }
        return new PageDialectSql(sqlBuilder.toString(), pagePair, sizePair);
    }
}
