package com.github.ethancarter.unipage.mybatis;

import com.github.ethancarter.unipage.statement.PaginationStatement;
import jakarta.annotation.Nullable;

import java.util.function.Function;

/**
 * mybatis 分页语句
 *
 * @author Ethan Carter
 * @date 2024/03/07
 */
public interface MybatisPaginationStatement extends PaginationStatement {

    @Override
    default String getLanguage() {
        return "JDBC Mybatis";
    }

    /**
     * 获取Mybatis映射语句 ID
     *
     * @return Mybatis映射语句 ID
     */
    String getNativeStatement();

    /**
     * 查询参数
     *
     * @return 参数
     */
    @Nullable
    Object getParameter();

    static <T> MybatisPaginationStatement of(Class<T> mapperClass,
                                             Function<MybatisMapperPaginationStatement.Builder<T>,
                                                     MybatisMapperPaginationStatement.Builder<T>> function) {
        return MybatisMapperPaginationStatement.of(mapperClass, function);
    }
}
