package cn.haigle.virtue.admin.role.dao.provider;


import cn.haigle.virtue.admin.role.entity.ao.AdminRoleAO;
import cn.haigle.virtue.admin.role.entity.vo.AdminRoleVO;
import cn.haigle.virtue.common.base.page.Page;
import cn.haigle.virtue.common.dao.DaoProvider;
import cn.haigle.virtue.common.util.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 角色管理sql
 * @author haigle
 * @date 2019-08-04 00:08
 */
public class AdminRoleDaoProvider extends DaoProvider {

    private static final String PRIMARY_KEY_COLUMN = "id";
    private static final String PRIMARY_KEY_VALUE = "#{m.id}";

    private static final String TABLE_REPORT = "sys_role";
    private static final String[] TABLE_REPORT_COLUMNS = {"name", "ename", "description"};
    private static final String[] TABLE_REPORT_VALUES = {"#{m.name}", "#{m.ename}", "#{m.description}"};

    private static final String TABLE_REPORT_NEXT = "sys_role_menu";

    public String list(Page<AdminRoleVO> page, String name) {
        SQL sql = new SQL();
        sql.SELECT(PRIMARY_KEY_COLUMN)
                .SELECT(TABLE_REPORT_COLUMNS)
                .SELECT(GMT_CREATE_COLUMN)
                .FROM(TABLE_REPORT);

        if(StringUtils.isNotEmpty(name)) {
            sql.WHERE("username like concat('%',#{name},'%')");
        }
        sql.ORDER_BY("create_time desc");
        String sqlString = sql.toString();
        return sqlString + " limit #{page.col},#{page.pageSize}";
    }

    public String getTotal(Page<AdminRoleVO> page, String name) {
        SQL sql = new SQL();
        sql.SELECT("count(*)")
                .FROM(TABLE_REPORT);

        if(StringUtils.isNotEmpty(name)) {
            sql.WHERE("username like concat('%',#{name},'%')");
        }
        return sql.toString();
    }

    public String save(AdminRoleAO m, Long uid) {
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

    public String update(AdminRoleAO m, Long uid) {
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

    public String saveRoleMenu(Long roleId, List<Long> menuList) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(TABLE_REPORT_NEXT).append("(role_id, menu_id)")
                .append("values");
        Long menuId;
        for (int i = 0; i < menuList.size(); i++) {
            menuId = menuList.get(i);
            if (i != 0) {
                sql.append(",");
            }
            sql.append("(").append(roleId).append(",").append(menuId).append(")");
        }
        return sql.toString();
    }

}
