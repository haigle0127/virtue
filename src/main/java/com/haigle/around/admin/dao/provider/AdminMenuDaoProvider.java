package com.haigle.around.admin.dao.provider;

import com.haigle.around.admin.entity.ao.AdminMenuAo;
import com.haigle.around.common.dao.DaoProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 * 菜单管理sql
 * @author haigle
 * @date 2019/8/23 10:15
 */
public class AdminMenuDaoProvider extends DaoProvider {

    private static final String PRIMARY_KEY_COLUMN = "id";
    private static final String PRIMARY_KEY_VALUE = "#{m.id}";

    private static final String TABLE_REPORT = "sys_menu";
    private static final String[] TABLE_REPORT_COLUMNS = {"parent_id", "name", "ename", "power", "description"};
    private static final String TABLE_HAS_CHILDREN_COLUMN = "has_children";
    private static final String[] TABLE_REPORT_VALUES = {"#{m.parentId}", "#{m.name}", "#{m.ename}", "#{m.power}", "#{m.description}"};

    private static final String[] TABLE_ALL_REPORT_COLUMNS = {"id", "parent_id", "name", "has_children"};

    private static final String TABLE_REPORT_NEXT = "sys_role_menu";

    public String list(Long id) {
        SQL sql = new SQL();
        sql.SELECT(PRIMARY_KEY_COLUMN)
                .SELECT(TABLE_REPORT_COLUMNS)
                .SELECT(TABLE_HAS_CHILDREN_COLUMN)
                .SELECT(GMT_CREATE_COLUMN)
                .FROM(TABLE_REPORT)
                .WHERE("parent_id = #{id}")
                .ORDER_BY("create_time DESC");
        return sql.toString();
    }

    public String allList() {
        SQL sql = new SQL();
        sql.SELECT(TABLE_ALL_REPORT_COLUMNS)
                .FROM(TABLE_REPORT);
        return sql.toString();
    }

    public String save(AdminMenuAo m, Long uid) {
        SQL sql = new SQL();
        sql.INSERT_INTO(TABLE_REPORT)
                .INTO_COLUMNS(PRIMARY_KEY_COLUMN)
                .INTO_COLUMNS(TABLE_REPORT_COLUMNS)
                .INTO_COLUMNS(CREATOR_GMT_COLUMNS)
                .INTO_COLUMNS(MODIFIER_GMT_COLUMNS)
                .INTO_VALUES(PRIMARY_KEY_VALUE)
                .INTO_VALUES(TABLE_REPORT_VALUES)
                .INTO_VALUES(CREATOR_GMT_VALUES)
                .INTO_VALUES(MODIFIER_GMT_VALUES);
        return sql.toString();
    }

    public String update(AdminMenuAo m, Long uid) {
        SQL sql = new SQL();
        sql.UPDATE(TABLE_REPORT);
        for (int i = 0; i < TABLE_REPORT_COLUMNS.length; i++) {
            sql.SET(TABLE_REPORT_COLUMNS[i] + "=" + TABLE_REPORT_VALUES[i]);
        }
        for (int i = 0; i < MODIFIER_GMT_COLUMNS.length; i++) {
            sql.SET(MODIFIER_GMT_COLUMNS[i] + "=" + MODIFIER_GMT_VALUES[i]);
        }
        sql.WHERE(PRIMARY_KEY_COLUMN + "=" + PRIMARY_KEY_VALUE);

        return sql.toString();
    }

}
