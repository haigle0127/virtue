package cn.haigle.virtue.system.dao.provider;

import cn.haigle.virtue.system.entity.ao.UserAo;
import cn.haigle.virtue.system.entity.dto.UserDto;
import cn.haigle.virtue.system.entity.vo.UserVo;
import cn.haigle.virtue.common.base.page.Page;
import cn.haigle.virtue.common.dao.DaoProvider;
import cn.haigle.virtue.common.util.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 用户管理sql
 * @author haigle
 * @date 2019-08-14 20:40
 */
public class UserDaoSqlProvider extends DaoProvider {

    private static final String PRIMARY_KEY_COLUMN = "id";
    private static final String PRIMARY_KEY_VALUE = "#{m.id}";

    private static final String TABLE_REPORT = "sys_user";
    private static final String[] TABLE_REPORT_COLUMNS = {"username", "phone", "email"};
    private static final String[] TABLE_REPORT_VALUES = {"#{m.username}", "#{m.phone}", "#{m.email}"};
    private static final String[] TABLE_REPORT_UPDATE_COLUMNS = {"password", "salt"};
    private static final String[] TABLE_REPORT_UPDATE_VALUES = {"#{m.password}", "#{m.salt}"};

    private static final String TABLE_REPORT_NEXT = "sys_user_role";

    private static final String[] TITLE = {"id", "username", "email", "phone", "avatar", "introduction", "birth", "education", "organize_id", "create_time"};

    public String list(Page<UserVo> page, String name) {
        SQL sql = new SQL();
        sql.SELECT(TITLE)
                .FROM(TABLE_REPORT);
        if(StringUtils.isNotEmpty(name)) {
            sql.WHERE("username like concat('%',#{name},'%')");
        }
        sql.ORDER_BY("create_time DESC");
        String sqlString = sql.toString();
        return sqlString + " limit #{page.col}, #{page.pageSize}";
    }

    public String getTotal(Page<UserVo> page, String name) {
        SQL sql = new SQL();
        sql.SELECT("count(*)")
                .FROM(TABLE_REPORT);

        if(StringUtils.isNotEmpty(name)) {
            sql.WHERE("username like concat('%',#{name},'%')");
        }
        return sql.toString();
    }

    public String save(UserDto m, Long uid) {
        SQL sql = new SQL();
        sql.INSERT_INTO(TABLE_REPORT)
                .INTO_COLUMNS(PRIMARY_KEY_COLUMN)
                .INTO_COLUMNS(TABLE_REPORT_COLUMNS)
                .INTO_COLUMNS(TABLE_REPORT_UPDATE_COLUMNS)
                .INTO_COLUMNS(CREATOR_GMT_COLUMNS)
                .INTO_COLUMNS(MODIFIER_GMT_COLUMNS)
                .INTO_VALUES(PRIMARY_KEY_VALUE)
                .INTO_VALUES(TABLE_REPORT_VALUES)
                .INTO_VALUES(TABLE_REPORT_UPDATE_VALUES)
                .INTO_VALUES(CREATOR_GMT_VALUES)
                .INTO_VALUES(MODIFIER_GMT_VALUES);
        return sql.toString();
    }

    public String update(UserAo m, Long uid) {
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

    public String saveUserRole(Long userId, List<Long> roleList) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(TABLE_REPORT_NEXT).append("(user_id, role_id)")
                .append("values");
        Long roleId;
        for (int i = 0; i < roleList.size(); i++) {
            roleId = roleList.get(i);
            if (i != 0) {
                sql.append(",");
            }
            sql.append("(").append(userId).append(",").append(roleId).append(")");
        }
        return sql.toString();
    }

}
