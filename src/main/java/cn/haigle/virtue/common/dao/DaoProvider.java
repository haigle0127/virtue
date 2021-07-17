package cn.haigle.virtue.common.dao;

/**
 * sql DAO父类
 * @author haigle
 * @date 2019/8/23 16:28
 */
public class DaoProvider {

    protected static final String[] CREATOR_GMT_COLUMNS = {"create_by", "create_time"};
    protected static final String[] CREATOR_GMT_VALUES = {"#{uid}", "now()"};
    protected static final String[] MODIFIER_GMT_COLUMNS = {"update_by", "update_time"};
    protected static final String[] MODIFIER_GMT_VALUES = {"#{uid}", "now()"};
    protected static final String GMT_CREATE_COLUMN = "create_time";

}
