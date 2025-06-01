package com.github.ethancarter.unipage.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分页映射
 *
 * @author Ethan Carter
 * @date 2024/02/04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface PaginationKey {

    /**
     * 分页唯一标识
     *
     * @return 唯一标识
     */
    String[] value();

}
