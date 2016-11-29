package cn.yueying.beautymessage.controller;

import cn.yueying.beautymessage.service.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by luojian on 2016/11/29.
 */
@Controller
public class ManageController {

    private final Logger logger = LoggerFactory.getLogger(ManageController.class);

    private ManageService manageService;

    @Autowired
    public ManageController(ManageService manageService) {
        this.manageService = manageService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        logger.debug("index() is executed!");
        if (model != null) {
            Iterator<String> it = model.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                logger.debug("$key: {}, $value: {}", key, model.get(key));
            }
        } else {
            logger.debug("model is null");
        }

        model.put("title", manageService.getTitle(""));
        model.put("msg", manageService.getDesc());
        return "index";
    }


    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {

        logger.debug("hello() is executed - $name {}", name);

        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        model.addObject("title", manageService.getTitle(name));
        model.addObject("msg", manageService.getDesc());

        return model;

    }
}
