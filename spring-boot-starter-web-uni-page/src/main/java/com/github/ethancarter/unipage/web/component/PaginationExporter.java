package com.github.ethancarter.unipage.web.component;

import com.github.ethancarter.unipage.result.set.PaginationResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 分页导出器
 *
 * @author Ethan Carter
 * @date 2025/05/26
 */
public interface PaginationExporter {

    /**
     * 导出分页数据
     *
     * @param resultSet  分页数据集
     * @param request  请求
     * @param response 响应
     */
    void exports(PaginationResultSet resultSet, HttpServletRequest request, HttpServletResponse response);
}
