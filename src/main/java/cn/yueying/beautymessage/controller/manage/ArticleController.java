package cn.yueying.beautymessage.controller.manage;

import cn.yueying.beautymessage.exception.BeautyException;
import cn.yueying.beautymessage.model.AjaxBaseModel;
import cn.yueying.beautymessage.model.Article;
import cn.yueying.beautymessage.model.Manager;
import cn.yueying.beautymessage.model.PageInfo;
import cn.yueying.beautymessage.service.ArticleService;
import cn.yueying.beautymessage.utils.Constants;
import cn.yueying.beautymessage.utils.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by luojian on 2017/4/19.
 */
@Controller
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/manage/article/publish_prepare", method = RequestMethod.GET)
    public ModelAndView publish_prepare(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Object obj = request.getSession().getAttribute(Constants.Manager.SESSION_USER);
        if (obj == null || !(obj instanceof Manager)) {
            mav.setViewName("redirect:/manage/");
            return mav;
        }
        mav.setViewName("manage/article/publish");
        mav.addObject("manager", obj);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/manage/article/publish", method = RequestMethod.POST)
    public AjaxBaseModel publish(HttpServletRequest request, Article article) {
        AjaxBaseModel model = new AjaxBaseModel();
        Object obj = request.getSession().getAttribute(Constants.Manager.SESSION_USER);
        if (obj == null || !(obj instanceof Manager)) {
            model.setCode(-1);
            model.setMsg("用户未登录");
            model.setC_url("/manage/");
        } else {
            try {
                articleService.publishArticle((Manager) obj, article);
                model.setCode(1);
                model.setMsg("发布成功");
                model.setC_url("/manage/article/list");
            } catch (BeautyException e) {
                model.setCode(2);
                model.setMsg("发布失败，" + e.getMessage());
            }
        }
        return model;
    }

    @RequestMapping(value = "/manage/article/list_prepare", method = RequestMethod.GET)
    public ModelAndView listPrepare(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Object obj = request.getSession().getAttribute(Constants.Manager.SESSION_USER);
        if (obj == null || !(obj instanceof Manager)) {
            mav.setViewName("redirect:/manage/");
            return mav;
        }
        mav.setViewName("manage/article/list");
        mav.addObject("manager", obj);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/manage/article/list", method = RequestMethod.POST)
    public PageInfo<Article> list(HttpServletRequest request, String draw, String start, String length, String keyword) {
        Object obj = request.getSession().getAttribute(Constants.Manager.SESSION_USER);
        if (obj == null || !(obj instanceof Manager)) {
            return null;
        }
        return articleService.listArticle(TextUtils.parseString2Int(draw), TextUtils.parseString2Int(start), TextUtils.parseString2Int(length), keyword);
    }


}
