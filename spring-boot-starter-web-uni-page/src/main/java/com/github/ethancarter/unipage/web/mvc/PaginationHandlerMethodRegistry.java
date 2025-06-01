package com.github.ethancarter.unipage.web.mvc;

import org.springframework.web.method.HandlerMethod;

import java.util.Set;

/**
 * 分页处理程序方法注册表
 *
 * @author Ethan Carter
 * @date 2025/05/28
 */
public interface PaginationHandlerMethodRegistry {

    Set<String> getAllPaginationKey();

    HandlerMethod getHandler(String paginationKey);

    void register(String paginationKey, HandlerMethod handlerMethod);
}
