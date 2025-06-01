package com.github.ethancarter.unipage.web.mvc;

import com.github.ethancarter.unipage.statement.PaginationStatement;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 分页操作处理程序
 *
 * @author Ethan Carter
 * @date 2025/05/28
 */
public interface PaginationActionHandler {

    String getAction();

    @Nullable
    Object handle(String paginationKey, PaginationStatement statement,
                  HttpServletRequest request, HttpServletResponse response);
}
