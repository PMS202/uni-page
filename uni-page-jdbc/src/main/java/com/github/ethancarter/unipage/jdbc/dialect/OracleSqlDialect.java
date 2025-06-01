package com.github.ethancarter.unipage.jdbc.dialect;

import com.github.ethancarter.unipage.domain.Pageable;
import com.github.ethancarter.unipage.jdbc.JdbcPaginationStatement;
import net.sf.jsqlparser.JSQLParserException;

import java.util.function.Function;

/**
 * oracle sql方言
 *
 * @author Ethan Carter
 * @date 2023/07/31
 */
public class OracleSqlDialect extends AbstractPaginationSqlDialect {

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
                .apply(new PageDialectSql.ParamPair(PAGE_SIZE_PARAM_NAME, pageable.getOffset() + pageable.getPageSize()));

        String modifySql;
        try {
            modifySql = getModifySql(jdbcStatement);
        } catch (JSQLParserException e) {
            logger.warn("Parsing sql [{}] exception", originalSql, e);
            return new PageDialectSql(originalSql, pagePair, sizePair);
        }
        // 分页SQL
        String sql = "SELECT * FROM ( SELECT TMP.*, ROWNUM ROW_ID FROM ( " +
                modifySql + " ) TMP WHERE ROWNUM <= :" + pagePair.getName() + ") WHERE ROW_ID > :" + sizePair.getName();
        return new PageDialectSql(sql, pagePair, sizePair);
    }
}
