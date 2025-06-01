package com.github.ethancarter.unipage.jdbc.sqlmodifier;


/**
 * sql修改器
 *
 * @author Ethan Carter
 * @date 2023/08/21
 */
public interface SqlModifier {

    /**
     * 修改
     *
     * @param sql sql
     * @return 修改后的sql
     */
    String modify(String sql);
}
