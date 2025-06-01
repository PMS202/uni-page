package com.github.ethancarter.unipage.mybatis.mapper;

import java.io.Serializable;

@FunctionalInterface
public interface UnaryFunction<T, P, R> extends Serializable {
    R apply(T t, P p);
}