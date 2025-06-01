package com.github.ethancarter.unipage.mybatis.mapper;

import java.io.Serializable;

@FunctionalInterface
public interface TernaryFunction<T, P1, P2, P3, R> extends Serializable {
    R apply(T t, P1 p1, P2 p2, P3 p3);
}