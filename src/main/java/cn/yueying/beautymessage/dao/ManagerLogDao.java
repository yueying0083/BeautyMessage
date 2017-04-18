package cn.yueying.beautymessage.dao;

import cn.yueying.beautymessage.model.ManagerLog;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

/**
 * Created by luojian on 2017/4/18.
 */
@Component
public class ManagerLogDao extends JdbcDaoSupport {

    public void save(ManagerLog log) {
        String sql = "INSERT INTO beauty_message.manager_log(manager_id, operation_id, operation_name, operation_time, operation_granted) VALUES(?,?,?,?,?)";
        getJdbcTemplate().update(sql, log.getManagerId(), log.getOperationId(), log.getOperationName(), log.getOperationTime(), log.getOperationGranted());
    }
}
