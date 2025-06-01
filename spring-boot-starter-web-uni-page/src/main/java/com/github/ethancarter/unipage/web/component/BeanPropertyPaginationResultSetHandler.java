package com.github.ethancarter.unipage.web.component;

import com.github.ethancarter.unipage.result.set.AbstractPaginationContentHandler;

/**
 * 对象属性分页结果集处理程序
 *
 * @author Ethan Carter
 * @date 2024/03/08
 */
public class BeanPropertyPaginationResultSetHandler<T> extends AbstractPaginationContentHandler<T> {

    private final Class<T> mapperClass;

    public BeanPropertyPaginationResultSetHandler(Class<T> mapperClass) {
        super(new BeanPropertyPaginationRowMapper<>(mapperClass));
        this.mapperClass = mapperClass;
    }

    public Class<T> getMapperClass() {
        return mapperClass;
    }

    public static <T> BeanPropertyPaginationResultSetHandler<T> newInstance(Class<T> mapperClass) {
        return new BeanPropertyPaginationResultSetHandler<>(mapperClass);
    }
}
