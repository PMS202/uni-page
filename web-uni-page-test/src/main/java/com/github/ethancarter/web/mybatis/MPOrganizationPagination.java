package com.github.ethancarter.web.mybatis;

import com.github.ethancarter.unipage.annotation.PaginationKey;
import com.github.ethancarter.unipage.domain.Sort;
import com.github.ethancarter.unipage.mybatis.MybatisPaginationStatement;
import com.github.ethancarter.unipage.result.set.*;
import com.github.ethancarter.unipage.statement.PaginationStatement;
import com.github.ethancarter.unipage.web.component.PaginationComponent;
import com.github.ethancarter.unipage.web.mvc.PaginationStatementController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PaginationStatementController
@PaginationKey("mp/organization")
public class MPOrganizationPagination implements PaginationComponent {

    private static final Logger log = LoggerFactory.getLogger(MPOrganizationPagination.class);

    @RequestMapping(value = "mp/organization", produces = "application/json")
    public PaginationStatement organization(@RequestParam int page, @RequestParam int size,
                                            @RequestParam(required = false) String name) {
        return MybatisPaginationStatement
                .of(OrganizationPaginationMapper.class, b -> b
                        .reference(OrganizationPaginationMapper::getOrganizations, name)
                        .pageable(page - 1, size, Sort.by("name"))
                );
    }

    @Override
    public PaginationResultSetHandler<?> getDataResultSetHandler() {
        return null;
    }

    @Override
    public PaginationRowMapper<?> getDataRowMapper() {
        return (metadata, prs, rowNum) -> {
            OrganizationDTO dto = new OrganizationDTO();
            dto.setId(prs.getString("id"));
            dto.setContactInfo(prs.getString("contact_name") + " " + prs.getString("contact_email"));
            return dto;
        };
    }

}
