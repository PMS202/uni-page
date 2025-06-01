package com.github.ethancarter.unipage.web.mvc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class DefaultPaginationUriResolver implements PaginationUriResolver {

    private String basePath = "";

    @Override
    public String constructUri(String pageKey, String action) {
        if (!pageKey.startsWith("/")) {
            pageKey = "/" + pageKey;
        }
        if (!pageKey.endsWith("/")) {
            pageKey += "/";
        }
        return basePath + pageKey + action;
    }

    @Override
    @Nullable
    public PaginationUriInfo resolve(String[] definePaginationKeys, String uri) {
        if (!isPaginationPath(uri)) {
            return null;
        }
        if (StringUtils.hasText(basePath)) {
            uri = uri.replaceFirst(basePath, "");
        }
        for (String defineKey : definePaginationKeys) {
            String tempKey = defineKey;
            if (!tempKey.startsWith("/")) {
                tempKey = "/" + defineKey;
            }
            if (!tempKey.endsWith("/")) {
                tempKey = tempKey + "/";
            }
            if (uri.startsWith(tempKey)) {
                return new PaginationUriInfo(basePath, defineKey, uri.replaceFirst(tempKey, ""));
            }
        }
        return null;
    }

    @Override
    public boolean isPaginationPath(String uri) {
        return uri.startsWith(basePath + "/");
    }

    public void setBasePath(String basePath) {
        Assert.hasText(basePath, "Base path must not be empty");
        if (basePath.endsWith("/")) {
            basePath = basePath.substring(0, basePath.length() - 1);
        }
        this.basePath = basePath;
    }

}
