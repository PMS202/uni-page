package com.github.ethancarter.unipage.mybatis.mapper;

import java.io.Serializable;

@FunctionalInterface
public interface BinaryFunction<T, P1, P2, R> extends Serializable {
    R apply(T t, P1 p1, P2 p2);
}
