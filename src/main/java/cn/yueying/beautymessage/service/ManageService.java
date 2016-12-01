package cn.yueying.beautymessage.service;

import cn.yueying.beautymessage.model.Manager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by luojian on 2016/11/29.
 */
@Service
public class ManageService {

    private static final Logger logger = LoggerFactory.getLogger(ManageService.class);

    public Manager login(String username, String password) {
        if("test".equals(username) && "test123".equals(password)){
            Manager manager = new Manager();
            manager.setId(1);
            manager.setUsername(username);
            manager.setHeadImg("");
            manager.setRollName("超级管理员");
            return manager;
        }
        return null;
    }
}
