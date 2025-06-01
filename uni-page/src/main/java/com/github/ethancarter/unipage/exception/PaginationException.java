package com.github.ethancarter.unipage.exception;


/**
 * 分页引发的异常
 *
 * @author Ethan Carter
 * @date 2024/02/04
 */
public class PaginationException extends RuntimeException {

    public PaginationException(String msg) {
        super(msg);
    }

    public PaginationException(Throwable cause) {
        super(cause);
    }

    public PaginationException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
