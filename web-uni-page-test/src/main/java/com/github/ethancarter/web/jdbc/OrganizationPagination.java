package com.github.ethancarter.web.jdbc;

import com.github.ethancarter.unipage.domain.Sort;
import com.github.ethancarter.unipage.jdbc.JdbcPaginationStatement;
import com.github.ethancarter.unipage.statement.PaginationStatement;
import com.github.ethancarter.unipage.web.mvc.PaginationStatementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PaginationStatementController
public class OrganizationPagination {

    @RequestMapping("organization")
    public PaginationStatement organization(@RequestParam int page, @RequestParam int size,
                                            @RequestParam(required = false) String name) {
        return JdbcPaginationStatement.of(b -> b
                .sql("select * from o_stock.organization where name like :name order by id desc")
                .paramMap("name", "%" + name + "%")
                .pageable(page - 1, size, Sort.by("id")));
    }
}
