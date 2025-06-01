package com.github.ethancarter.unipage.mybatis.parameter;

import com.github.ethancarter.unipage.mybatis.mapper.MapperStatementInfo;
import org.apache.ibatis.reflection.ParamNameResolver;
import org.apache.ibatis.session.Configuration;

/**
 * Mybatis 参数名称解析器供应商
 *
 * @author Ethan Carter
 * @date 2024/03/29
 */
public class MybatisParamNameResolverSupplier implements ParamNameResolverSupplier {

    private static class ParamNameResolverSupplierHolder {
        private static final MybatisParamNameResolverSupplier INSTANCE = new MybatisParamNameResolverSupplier();
    }

    public static MybatisParamNameResolverSupplier getInstance() {
        return ParamNameResolverSupplierHolder.INSTANCE;
    }

    private MybatisParamNameResolverSupplier() {
    }

    @Override
    public Object get(MapperStatementInfo info, Object[] parameters) {
        return new ParamNameResolver(new Configuration(), info.getMapperStatementMethod()).getNamedParams(parameters);
    }
}
