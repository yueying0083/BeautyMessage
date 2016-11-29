package cn.yueying.beautymessage.service;

import cn.yueying.beautymessage.utils.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by luojian on 2016/11/29.
 */
@Service
public class ManageService {

    private static final Logger logger = LoggerFactory.getLogger(ManageService.class);

    public String getDesc() {

        logger.debug("getDesc() is executed!");

        return "Gradle + Spring MVC Hello World Example";

    }

    public String getTitle(String name) {

        logger.debug("getTitle() is executed! $name : {}", name);

        if (TextUtils.isEmpty(name)) {
            return "Hello World";
        } else {
            return "Hello " + name;
        }

    }
}
