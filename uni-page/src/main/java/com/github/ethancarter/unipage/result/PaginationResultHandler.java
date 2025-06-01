package com.github.ethancarter.unipage.result;

/**
 * 分页结果处理程序标识
 *
 * @author Ethan Carter
 * @date 2023/07/28
 */
public interface PaginationResultHandler<T, R> {

    /**
     * 分页结果处理
     *
     * @param content 内容
     * @return 返回结果
     */
    R handle(T content);
}
