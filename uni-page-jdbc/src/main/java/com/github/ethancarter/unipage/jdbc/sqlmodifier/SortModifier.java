package com.github.ethancarter.unipage.jdbc.sqlmodifier;

import com.github.ethancarter.unipage.domain.Sort;
import net.sf.jsqlparser.statement.select.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 订单修改器
 *
 * @author Ethan Carter
 * @date 2023/08/21
 */
public class SortModifier implements SelectSqlModifier {
    private static final Logger log = LoggerFactory.getLogger(SortModifier.class);
    private final Sort sort;
    /**
     * 不应排序
     */
    private final List<String> shouldNotSort = new ArrayList<>();

    public SortModifier(Sort sort) {
        this.sort = sort;
    }

    @Override
    public String modify(Select select) {
        String sql = select.toString();
        try {
            SelectBody selectBody = select.getSelectBody();
            //处理body-去最外层order by
            List<OrderByElement> orderByElements = extraOrderBy(selectBody);
            String defaultOrderBy = PlainSelect.orderByToString(orderByElements);
            for (String e : shouldNotSort) {
                if (defaultOrderBy.contains(e)) {
                    throw new IllegalArgumentException("原SQL[" + sql + "]中的order by包含参数，" +
                            "因此不能修改OrderBy!");
                }
            }
            // 新的sql
            sql = select.toString();
        } catch (Throwable e) {
            log.warn("处理排序失败: 降级为直接拼接 order by 参数", e);
        }
        StringJoiner orderBy = new StringJoiner(",");
        for (Sort.Order order : sort) {
            orderBy.add(String.format("%s %s", order.getProperty(), order.getDirection()));
        }
        return sql + " order by " + orderBy;
    }


    /**
     * extra order by and set default orderby to null
     *
     * @param selectBody
     */
    public List<OrderByElement> extraOrderBy(SelectBody selectBody) {
        if (selectBody instanceof PlainSelect) {
            List<OrderByElement> orderByElements = ((PlainSelect) selectBody).getOrderByElements();
            ((PlainSelect) selectBody).setOrderByElements(null);
            return orderByElements;
        } else if (selectBody instanceof WithItem) {
            WithItem withItem = (WithItem) selectBody;
            if (withItem.getSelectBody() != null) {
                return extraOrderBy(withItem.getSelectBody());
            }
        } else {
            SetOperationList operationList = (SetOperationList) selectBody;
            if (operationList.getSelects() != null && !operationList.getSelects().isEmpty()) {
                List<SelectBody> plainSelects = operationList.getSelects();
                return extraOrderBy(plainSelects.get(plainSelects.size() - 1));
            }
        }
        return null;
    }
}
