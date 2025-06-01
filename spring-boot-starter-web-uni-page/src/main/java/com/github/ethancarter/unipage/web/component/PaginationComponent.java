package com.github.ethancarter.unipage.web.component;

import com.github.ethancarter.unipage.result.set.PaginationResultSetHandler;
import com.github.ethancarter.unipage.result.set.PaginationRowMapper;
import org.springframework.lang.Nullable;

/**
 * 分页组件
 *
 * @author Ethan Carter
 * @date 2025/05/26
 */
public interface PaginationComponent {

    /**
     * 获取数据分页结果集处理程序
     *
     * @return 分页结果集处理程序
     */
    @Nullable
    default PaginationResultSetHandler<?> getDataResultSetHandler() {
        return null;
    }

    /**
     * 获取数据行映射器
     *
     * @return 分页行映射器
     */
    @Nullable
    default PaginationRowMapper<?> getDataRowMapper() {
        return null;
    }

    /**
     * 获取导出器
     *
     * @return 分页导出器
     */
    @Nullable
    default PaginationExporter getExporter()  {
        return null;
    }
}
