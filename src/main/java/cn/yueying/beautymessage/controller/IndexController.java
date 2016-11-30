package cn.yueying.beautymessage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by luojian on 2016/11/30.
 */
@Controller
public class IndexController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "redirect:/manage/";
    }

    @RequestMapping(value = "/s", method = RequestMethod.GET)
    public String s(){
        return "index";
    }
}
