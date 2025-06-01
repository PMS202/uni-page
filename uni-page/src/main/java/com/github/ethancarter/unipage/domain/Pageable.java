package com.github.ethancarter.unipage.domain;


import jakarta.annotation.Nullable;

/**
 * 可分页
 *
 * @author Ethan Carter
 * @date 2023/07/27
 */
public interface Pageable {

    /**
     * 分页页码, 起始值为0
     */
    int getPageNumber();

    /**
     * 分页大小
     */
    int getPageSize();

    /**
     * 根据分页页码和页大小取的偏移量
     */
    long getOffset();

    /**
     * 返回上一页分页信息
     */
    Pageable first();

    /**
     * 返回下一页分页信息
     */
    Pageable next();

    /**
     * 返回上一页或首页
     */
    Pageable previousOrFirst();

    /**
     * 返回是否有上一页
     */
    boolean hasPrevious();

    /**
     * 排序参数
     */
    @Nullable
    Sort getSort();

}
