package cn.yueying.beautymessage.controller.manage;

import cn.yueying.beautymessage.model.Manager;
import cn.yueying.beautymessage.service.ManageService;
import cn.yueying.beautymessage.utils.Constants;
import cn.yueying.beautymessage.utils.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by luojian on 2016/11/30.
 */
@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private ManageService manageService;

    @Autowired
    public LoginController(ManageService manageService) {
        this.manageService = manageService;
    }

    @RequestMapping(value = "/manage/", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Object obj = request.getSession().getAttribute(Constants.Manager.SESSION_USER);
        if (obj == null || !(obj instanceof Manager)) {
            mav.setViewName("manage/login");
            return mav;
        }
        mav.setViewName("index");
        mav.addObject("manager", obj);
        return mav;
    }

    @RequestMapping(value = "/manage/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        ModelAndView mav = new ModelAndView();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            mav.setViewName("manage/login");
            mav.addObject("error", "用户名/密码不能为空");
            return mav;
        }

        Manager manager = manageService.login(username, password);
        if(manager == null){
            mav.setViewName("manage/login");
            mav.addObject("error", "用户名/密码错误");
            return mav;
        }

        request.getSession().setAttribute(Constants.Manager.SESSION_USER, manager);
        mav.setViewName("index");
        mav.addObject("manager", manager);
        return mav;
    }


}