package com.github.ethancarter.unipage.web.component;

import org.springframework.lang.Nullable;

/**
 * 分页语句注册表
 *
 * @author Ethan Carter
 * @date 2025/05/26
 */
public interface PaginationComponentFactory {

    /**
     * 获取默认分页组件
     *
     * @return 分页组件
     */
    @Nullable
    default PaginationComponent getDefaultPaginationComponent() {
        return new DefaultPaginationComponent();
    }

    /**
     * 获取分页组件
     *
     * @param paginationKey 分页键
     * @return 分页组件
     */
    @Nullable
    PaginationComponent getPaginationComponent(String paginationKey);

}
