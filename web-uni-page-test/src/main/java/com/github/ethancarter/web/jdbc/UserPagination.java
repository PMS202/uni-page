package com.github.ethancarter.web.jdbc;


import static com.github.ethancarter.unipage.jdbc.NamedParameterJdbcStatement.of;

import com.github.ethancarter.unipage.web.mvc.PaginationStatementController;
import com.github.ethancarter.unipage.statement.PaginationStatement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@PaginationStatementController
public class UserPagination {

    @PostMapping({"mongodb/user", "user"})
    public PaginationStatement user(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                    @RequestParam(value = "mobile", required = false) String mobile) {
        return of(b -> b
                .sql("SELECT * FROM users WHERE mobile LIKE :p1")
                .paramMap("mobile", "%" + mobile + "%")
                .pageable(page-1, size));

    }

    @PostMapping("user-operation-log")
    public PaginationStatement userOperationLog(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "account") String account) {
        return of(b -> b
                .sql("SELECT * FROM oplogs WHERE account = :mobile")
                .paramMap("account", account)
                .pageable(page, size));

    }

    @PostMapping(value = "user-roles")
    public PaginationStatement userRoles(@RequestBody @Valid UserRoleDTO dto) {
        return of(b -> b
                .sql("SELECT *" +
                        " FROM (SELECT g.id, g.title" +
                        "      FROM usergroups g" +
                        "               LEFT JOIN useringroups ug ON g.id = ug.usergroupid" +
                        "               LEFT JOIN users u ON ug.account = u.account" +
                        "      WHERE g.title = :roleName" +
                        "      ) t" +
                        " group by t.id")
                .paramMap("username", dto.getAccount(), "roleName", dto.getRoleName())
                .pageable(dto.getPageNum(), dto.getPageSize()));

    }
}
