package com.github.ethancarter.web.jdbc;

import com.github.ethancarter.unipage.annotation.PaginationKey;
import com.github.ethancarter.unipage.result.set.PaginationRowMapper;
import com.github.ethancarter.unipage.web.component.PaginationComponent;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@PaginationKey("user")
public class UserPaginationComponent implements PaginationComponent {

    @Override
    public PaginationRowMapper<?> getDataRowMapper() {
        return (metadata, prs, rowNum) -> {
            Properties properties = new Properties();
            return properties;
        };
    }
}
