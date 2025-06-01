package com.github.ethancarter.unipage.jdbc.dialect;

import com.github.ethancarter.unipage.domain.Pageable;
import com.github.ethancarter.unipage.jdbc.JdbcPaginationStatement;
import net.sf.jsqlparser.JSQLParserException;

import java.util.function.Function;

/**
 * MySQL方言
 *
 * @author Ethan Carter
 * @date 2024/03/08
 */
public class MysqlDialect extends AbstractPaginationSqlDialect {

    @Override
    public PageDialectSql getPageDialectSql(JdbcPaginationStatement jdbcStatement,
                                            Function<PageDialectSql.ParamPair, PageDialectSql.ParamPair> pagePairFunc,
                                            Function<PageDialectSql.ParamPair, PageDialectSql.ParamPair> sizePairFunc) {
        String originalSql = jdbcStatement.getNativeStatement();
        Pageable pageable = jdbcStatement.getPageable();

        // 分页参数
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        PageDialectSql.ParamPair pagePair = pagePairFunc
                .apply(new PageDialectSql.ParamPair(PAGE_NUMBER_PARAM_NAME, pageable.getOffset()));
        PageDialectSql.ParamPair sizePair = sizePairFunc
                .apply(new PageDialectSql.ParamPair(PAGE_SIZE_PARAM_NAME, size));

        String modifySql;
        try {
            modifySql = getModifySql(jdbcStatement);
        } catch (JSQLParserException e) {
            logger.warn("Parsing sql [{}] exception", originalSql, e);
            return new PageDialectSql(originalSql, pagePair, sizePair);
        }
        // 修改SQL
        // 分页SQL
        StringBuilder sqlBuilder = new StringBuilder(modifySql.length() + 14);
        sqlBuilder.append(modifySql);

        if (page == 0) {
            sqlBuilder.append("\n LIMIT :").append(sizePair.getName());
        } else {
            sqlBuilder.append("\n LIMIT :").append(pagePair.getName()).append(", :").append(sizePair.getName());
        }
        return new PageDialectSql(sqlBuilder.toString(), pagePair, sizePair);
    }
}
