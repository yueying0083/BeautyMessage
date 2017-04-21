package cn.yueying.beautymessage.service;

import cn.yueying.beautymessage.dao.ManagerDao;
import cn.yueying.beautymessage.dao.ManagerLogDao;
import cn.yueying.beautymessage.exception.BeautyException;
import cn.yueying.beautymessage.model.Manager;
import cn.yueying.beautymessage.model.ManagerLog;
import cn.yueying.beautymessage.privilege.ManagerPrivilege;
import cn.yueying.beautymessage.utils.Md5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by luojian on 2016/11/29.
 */
@Service
public class ManageService {

    private static final Logger logger = LoggerFactory.getLogger(ManageService.class);

    private final ManagerDao managerDao;
    private final ManagerLogDao managerLogDao;

    @Autowired
    public ManageService(ManagerDao managerDao, ManagerLogDao managerLogDao) {
        this.managerDao = managerDao;
        this.managerLogDao = managerLogDao;
    }

    public Manager login(String username, String password, String ip) throws BeautyException {
        List<Manager> list = managerDao.findByUsername(username);
        logger.debug("query db list size = {}", list == null ? 0 : list.size());

        if (list != null) {
            for (Manager m : list) {
                logger.debug("query db username matching username 1 = {}, username 2 = {}, match = {}", username, m.getUsername(), Objects.equals(username, m.getUsername()));
                if (Objects.equals(username, m.getUsername())) {
                    logger.debug("query db username matched {}, password 1 = {}, password 2 = {}, match = {}", username, Md5.encode(password), m.getPassword(), Objects.equals(Md5.encode(password), m.getPassword()));
                    if (Objects.equals(Md5.encode(password), m.getPassword())) {
                        m.setIp(ip);
                        ManagerLog log = ManagerPrivilege.requestPermission(m, ManagerPrivilege.MANAGER_LOGIN, "登录系统");
                        managerLogDao.save(log);
                        if (log.granted()) {
                            return m;
                        }
                        throw new BeautyException("用户被禁止!");
                    }
                }
            }
        }
        throw new BeautyException("用户名/密码错误");
    }

    public Manager getSpiderUser(){
        Manager m = new Manager();
        m.setId(2);
        m.setUsername("自动抓取程序");
        m.setPrivilege(4294967295L);
        m.setIp("127.0.0.1");
        return m;
    }
}
