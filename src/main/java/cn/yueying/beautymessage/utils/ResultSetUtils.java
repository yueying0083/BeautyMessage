package cn.yueying.beautymessage.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by luojian on 2017/4/19.
 */
public class ResultSetUtils {

    /**
     * 判断查询结果集中是否存在某列
     * @param rs 查询结果集
     * @param columnName 列名
     * @return true 存在; false 不存咋
     */
    public static boolean isExistColumn(ResultSet rs, String columnName) {
        try {
            if (rs.findColumn(columnName) > 0 ) {
                return true;
            }
        }
        catch (SQLException e) {
            return false;
        }

        return false;
    }


}
