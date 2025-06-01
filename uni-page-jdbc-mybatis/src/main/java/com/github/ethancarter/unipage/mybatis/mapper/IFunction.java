package com.github.ethancarter.unipage.mybatis.mapper;

import java.io.Serializable;

@FunctionalInterface
public interface IFunction<T, R> extends Serializable {
    R apply(T t);
}