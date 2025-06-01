package com.github.ethancarter.unipage.mybatis;

import com.github.ethancarter.unipage.mybatis.mapper.*;
import com.github.ethancarter.unipage.mybatis.parameter.MybatisParamNameResolverSupplier;
import com.github.ethancarter.unipage.mybatis.parameter.ParamNameResolverSupplier;
import com.github.ethancarter.unipage.statement.BasePaginationStatement;
import com.github.ethancarter.unipage.statement.BasePaginationStatementBuilder;
import com.github.ethancarter.unipage.util.Assert;
import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Mybatis 分页语句
 *
 * @author Ethan Carter
 * @date 2024/03/07
 */
public class MybatisMapperPaginationStatement extends BasePaginationStatement implements MybatisPaginationStatement {

    /**
     * 映射语句 ID
     */
    private final String mappedStatementId;
    /**
     * 参数
     */
    @Nullable
    private final Object parameter;

    private MybatisMapperPaginationStatement(Builder<?> builder) {
        super(builder.getPageable());
        Assert.notEmpty(builder.mappedStatementId, "mappedStatementId must not be empty!");
        this.mappedStatementId = builder.mappedStatementId;
        this.parameter = builder.parameterObject;
    }

    @Override
    public String getNativeStatement() {
        return mappedStatementId;
    }

    @Override
    @Nullable
    public Object getParameter() {
        return parameter;
    }

    public static <T> MybatisMapperPaginationStatement of(Class<T> mapperClass,
                                                          Function<Builder<T>, Builder<T>> function) {
        return new MapperStatementBuilder<>(mapperClass).statement(function);
    }

    /**
     * Mapper 语句生成器
     *
     * @author Ethan Carter
     * @date 2024/04/09
     */
    public static class MapperStatementBuilder<T> {
        private final Class<T> mapperClass;

        public MapperStatementBuilder(Class<T> mapperClass) {
            this.mapperClass = mapperClass;
        }

        public MybatisMapperPaginationStatement statement(Function<Builder<T>, Builder<T>> function) {
            return function.apply(new Builder<>()).build();
        }

        public Class<T> getMapperClass() {
            return mapperClass;
        }
    }

    public static class Builder<T>
            extends BasePaginationStatementBuilder<MybatisMapperPaginationStatement, Builder<T>> {

        private String mappedStatementId;
        private Object parameterObject;
        private Object[] params;
        @Nullable
        private ParamNameResolverSupplier paramNameResolver;

        public Builder<T> mapper(String mappedStatementId) {
            this.mappedStatementId = mappedStatementId;
            return self();
        }

        public Builder<T> id(String mappedStatementId, Object... params) {
            this.mappedStatementId = mappedStatementId;
            this.params = params;
            return self();
        }

        private Builder<T> mapper(Serializable mappedStatement) {
            return mapper(getSerializableMapperStatementId(mappedStatement));
        }

        private Builder<T> mapper(Serializable mappedStatement, Object... params) {
            return id(getSerializableMapperStatementId(mappedStatement), params);
        }

        public <R> Builder<T> reference(IFunction<T, R> function) {
            return mapper(function);
        }

        public <P, R> Builder<T> reference(UnaryFunction<T, P, R> function, P p) {
            return mapper(function, p);
        }

        public <P1, P2, R> Builder<T> reference(BinaryFunction<T, P1, P2, R> function, P1 p1, P2 p2) {
            return mapper(function, p1, p2);
        }

        public <P1, P2, P3, R> Builder<T> reference(TernaryFunction<T, P1, P2, P3, R> function,
                                                    P1 p1, P2 p2, P3 p3) {
            return mapper(function, p1, p2, p3);
        }

        public <P1, P2, P3, P4, R> Builder<T> reference(QuaternaryFunction<T, P1, P2, P3, P4, R> function,
                                                        P1 p1, P2 p2, P3 p3, P4 p4) {
            return mapper(function, p1, p2, p3, p4);
        }

        public <P1, P2, P3, P4, P5, R> Builder<T> reference(QuinaryFunction<T, P1, P2, P3, P4, P5, R> function,
                                                            P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
            return mapper(function, p1, p2, p3, p4, p5);
        }

        public <P1, P2, P3, P4, P5, P6, R> Builder<T> reference(SenaryFunction<T, P1, P2, P3, P4, P5, P6, R> function,
                                                                P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6) {
            return mapper(function, p1, p2, p3, p4, p5, p6);
        }

        public Builder<T> paramNameResolver(@Nullable ParamNameResolverSupplier paramNameResolver) {
            this.paramNameResolver = paramNameResolver;
            return self();
        }

        @Override
        protected Builder<T> self() {
            return this;
        }

        @Override
        public MybatisMapperPaginationStatement build() {
            if (this.params != null) {
                ParamNameResolverSupplier resolver = getParamNameResolver();
                Assert.notNull(resolver, "ParamNameResolverSupplier must not be null.");
                this.parameterObject = resolver.get(new MapperStatementInfoImpl(mappedStatementId), params);
            }
            return new MybatisMapperPaginationStatement(this);
        }

        protected ParamNameResolverSupplier getParamNameResolver() {
            if (this.paramNameResolver != null) {
                return paramNameResolver;
            } else {
                return MybatisParamNameResolverSupplier.getInstance();
            }
        }

        private String getSerializableMapperStatementId(Serializable serializable) {
            SerializedMapperStatementInfo info = new SerializedMapperStatementInfo(serializable);
            return info.getReference();
        }
    }
}
