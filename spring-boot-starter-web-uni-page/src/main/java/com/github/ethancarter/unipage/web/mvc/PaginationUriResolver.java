package com.github.ethancarter.unipage.web.mvc;

import org.springframework.lang.Nullable;

/**
 * 分页 URL 解析器
 *
 * @author Ethan Carter
 * @date 2025/05/28
 */
public interface PaginationUriResolver {

    /**
     * 构造分页 URL
     */
    String constructUri(String pageKey, String action);

    /**
     * 解析分页请求信息
     *
     * @param definePaginationKeys 定义的分页键
     * @param uri                  URI
     * @return 分页请求信息
     */
    @Nullable
    PaginationUriInfo resolve(String[] definePaginationKeys, String uri);

    /**
     * 是分页路径
     *
     * @param uri URI
     * @return boolean
     */
    boolean isPaginationPath(String uri);
}