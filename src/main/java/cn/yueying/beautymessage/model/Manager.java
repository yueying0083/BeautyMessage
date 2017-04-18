package cn.yueying.beautymessage.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by luojian on 2016/11/30.
 */
public class Manager implements java.io.Serializable {

    private static final long serialVersionUID = 3387156133251951584L;
    private int id;
    private String username;
    private String password;
    private String avatar;
    private long privilege;
    private String rollName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getPrivilege() {
        return privilege;
    }

    public void setPrivilege(long privilege) {
        this.privilege = privilege;
    }

    public String getRollName() {
        return rollName;
    }

    public void setRollName(String rollName) {
        this.rollName = rollName;
    }

    public static final RowMapper<Manager> s_managerRowMapper = new RowMapper<Manager>(){

        @Override
        public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
            Manager m = new Manager();
            m.id = rs.getInt("id");
            m.username = rs.getString("username");
            m.password = rs.getString("password");
            m.avatar = rs.getString("avatar");
            m.privilege = rs.getLong("privilege");
            m.rollName = rs.getString("roll_name");
            return m;
        }
    };

}
