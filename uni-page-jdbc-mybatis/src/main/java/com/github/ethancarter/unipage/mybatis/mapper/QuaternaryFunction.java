package com.github.ethancarter.unipage.mybatis.mapper;

import java.io.Serializable;

@FunctionalInterface
public interface QuaternaryFunction<T, P1, P2, P3, P4, R> extends Serializable {
    R apply(T t, P1 p1, P2 p2, P3 p3, P4 p4);
}
