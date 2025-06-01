package com.github.ethancarter.unipage.mybatis;

import com.github.ethancarter.unipage.domain.PageImpl;
import com.github.ethancarter.unipage.domain.PageInformation;
import com.github.ethancarter.unipage.domain.Sort;
import com.github.ethancarter.unipage.result.set.*;
import com.github.ethancarter.unipage.statement.PaginationStatement;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.github.ethancarter.unipage.domain.Pageable;
import com.github.ethancarter.unipage.exception.PaginationException;
import com.github.ethancarter.unipage.executor.PaginationStatementExecutor;
import org.apache.ibatis.session.SqlSession;

import java.util.StringJoiner;

/**
 * mybatis 分页语句执行器
 *
 * @author Ethan Carter
 * @date 2024/03/07
 */
public class MybatisPaginationInterceptorExecutor implements PaginationStatementExecutor<MybatisPaginationStatement> {

    private final SqlSession sqlSession;

    public MybatisPaginationInterceptorExecutor(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public boolean supports(PaginationStatement paginationStatement) {
        return paginationStatement instanceof MybatisPaginationStatement;
    }

    @Override
    public PageInformation executeForInformation(MybatisPaginationStatement statement) throws PaginationException {
        String mappedStatementId = statement.getNativeStatement();
        Object parameter = statement.getParameter();
        Page<?> page;
        try (Page<Object> close = PageMethod.startPage(0, 0)) {
            page = (Page<?>) sqlSession.selectList(mappedStatementId, parameter);
        }
        if (page == null) {
            throw new MybatisPaginationException("The result must be a Page.");
        }
        return new PageImpl<>(page.getTotal(), statement.getPageable());
    }

    @Override
    public PaginationResultSet executeForResultSet(MybatisPaginationStatement statement) throws PaginationException {
        Pageable pageable = statement.getPageable();
        int pageNumber = pageable.getPageNumber() == 0 ? 1 : pageable.getPageNumber();
        String mappedStatementId = statement.getNativeStatement();
        Page<?> pageResult;
        try (ExecutorContext.Mark mark = ExecutorContext.pageMark();
             Page<Object> page = PageMethod.startPage(pageNumber, pageable.getPageSize())) {
            if (pageable.getSort() != null && pageable.getSort().isSorted()) {
                StringJoiner orderBy = new StringJoiner(",");
                for (Sort.Order order : pageable.getSort()) {
                    orderBy.add(String.format("%s %s", order.getProperty(), order.getDirection()));
                }
                page.setOrderBy(orderBy.toString());
            }
            pageResult = (Page<?>) sqlSession.selectList(mappedStatementId, statement.getParameter());
        }
        if (pageResult == null) {
            throw new MybatisPaginationException("The result must be a Page.");
        }
        if (pageResult.isEmpty()) {
            return new EmptyPaginationResultSet(pageable);
        }
        // ResultSetHandlerInterceptor
        Result result = (Result) pageResult.getResult().get(0);
        return new DefaultPaginationResultSet(pageResult.getTotal(),
                new DefaultPaginationResultSetMetadata(pageable, result.getColumns()), result.getRows());
    }
}
