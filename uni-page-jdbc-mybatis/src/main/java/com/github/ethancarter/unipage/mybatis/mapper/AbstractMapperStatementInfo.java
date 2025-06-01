package com.github.ethancarter.unipage.mybatis.mapper;

import com.github.ethancarter.unipage.mybatis.util.RefUtils;

import java.lang.reflect.Method;

/**
 * 抽象映射器语句信息
 *
 * @author Ethan Carter
 * @date 2024/03/29
 */
public abstract class AbstractMapperStatementInfo implements MapperStatementInfo {

    @Override
    public Method getMapperStatementMethod() {
        Method method = RefUtils.findMethod(getClasses(), getMethodName());
        if (method == null) {
            throw new IllegalArgumentException("No [" +
                    getClasses().getName() + "." + getMethodName() + "] mapper method");
        }
        return method;
    }
}
