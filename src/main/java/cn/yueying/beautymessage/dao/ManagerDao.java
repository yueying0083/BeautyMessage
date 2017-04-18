package cn.yueying.beautymessage.dao;

import cn.yueying.beautymessage.model.Manager;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by luojian on 2017/4/18.
 */
@Component("managerDao")
public class ManagerDao extends JdbcDaoSupport {

    public void add(Manager manager) {
        DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_MANAGER);

        String sql = "INSERT INTO beauty_message.manager(username, password, avatar) VALUES(?, ?, ?)";

        getJdbcTemplate().update(sql, manager.getUsername(), manager.getPassword(), manager.getAvatar());
    }

    public List<Manager> findByUsername(String username) {
        DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_MANAGER);
        String sql = "SELECT * FROM beauty_message.manager WHERE username = ?";

        return getJdbcTemplate().query(sql, new Object[]{username}, Manager.s_managerRowMapper);
    }

}
