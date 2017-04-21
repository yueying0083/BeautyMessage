package cn.yueying.beautymessage.privilege;

import cn.yueying.beautymessage.model.Manager;
import cn.yueying.beautymessage.model.ManagerLog;

import java.util.Date;

/**
 * Created by luojian on 2017/4/18.
 */
public class ManagerPrivilege {
    public static final long MANAGER_LOGIN = 0x1; // 0001
    public static final long MANAGER_LOGOUT = 0x2;// 0010
    public static final long MANAGER_PUBLISH_ARTICLE = 0X4;// 0100
    public static final long MANAGER_EDIT_ARTICLE = 0X8;// 1000

    public static final long PRIVILEGE_ALL = 0xFFFFFFFF;// 1111 1111 1111 1111 1111 1111 1111 1111


    /**
     * 构造一条管理员操作记录（granted 为是否通过）
     *
     * @param m
     * @param authority
     * @return
     */
    public static ManagerLog requestPermission(Manager m, long authority, String authorityStr) {
        if (m == null) {
            return null;// Impossible
        }
        return new ManagerLog(m.getId(), authority, authorityStr, new Date(System.currentTimeMillis()), canDo(m.getPrivilege(), authority), m.getIp());
    }

    private static int canDo(long privilege, long authority) {
        return (privilege & authority) > 0L ? 1 : 0;
    }

}
