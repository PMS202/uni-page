package com.github.ethancarter.unipage.mybatis;

import com.github.ethancarter.unipage.exception.PaginationException;

/**
 * MyBatis 分页异常
 *
 * @author Ethan Carter
 * @date 2024/03/08
 */
public class MybatisPaginationException extends PaginationException {
    /**
     * MyBatis 分页异常
     *
     * @param msg 异常消息
     */
    public MybatisPaginationException(String msg) {
        super(msg);
    }
}
