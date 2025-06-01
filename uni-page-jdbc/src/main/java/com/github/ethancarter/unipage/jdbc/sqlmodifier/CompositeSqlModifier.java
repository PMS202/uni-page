package com.github.ethancarter.unipage.jdbc.sqlmodifier;

import net.sf.jsqlparser.statement.select.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * 复合sql修改器
 *
 * @author Ethan Carter
 * @date 2023/08/21
 */
public class CompositeSqlModifier implements SelectSqlModifier {

    private final List<SelectSqlModifier> modifiers;

    public CompositeSqlModifier() {
        this.modifiers = new ArrayList<>();
    }

    public void addModifier(SelectSqlModifier modifier) {
        this.modifiers.add(modifier);
    }

    @Override
    public String modify(Select select) {
        String modifiedSql = select.toString();
        for (SelectSqlModifier modifier : modifiers) {
            modifiedSql = modifier.modify(select);
        }
        return modifiedSql;
    }
}
