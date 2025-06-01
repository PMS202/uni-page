package com.github.ethancarter.unipage.web.mvc;

import lombok.Data;

@Data
public class PaginationUriInfo {

    private final String basePath;
    private final String paginationKey;
    private final String action;

    public boolean isValid() {
        return paginationKey != null && action != null;
    }

    @Override
    public String toString() {
        return "[PaginationRequestInfo] paginationKey=" + paginationKey + ", action=" + action;
    }
}
