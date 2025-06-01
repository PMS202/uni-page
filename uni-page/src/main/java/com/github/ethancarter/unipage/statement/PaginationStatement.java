package com.github.ethancarter.unipage.statement;

import com.github.ethancarter.unipage.domain.Pageable;

/**
 * 分页语句
 *
 * @author Ethan Carter
 * @date 2023/07/28
 */
public interface PaginationStatement {

    /**
     * 获取语言
     *
     * @return 语言
     */
    default String getLanguage() {
        return "";
    }

    /**
     * 返回原生语句
     *
     * @return 原生语句
     */
    Object getNativeStatement();

    /**
     * 返回分页信息
     *
     * @return {@link Pageable}
     */
    Pageable getPageable();
}
