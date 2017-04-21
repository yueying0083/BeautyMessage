package cn.yueying.beautymessage.service;

import cn.yueying.beautymessage.dao.ArticleDao;
import cn.yueying.beautymessage.dao.ManagerDao;
import cn.yueying.beautymessage.dao.ManagerLogDao;
import cn.yueying.beautymessage.exception.BeautyException;
import cn.yueying.beautymessage.model.*;
import cn.yueying.beautymessage.privilege.ManagerPrivilege;
import cn.yueying.beautymessage.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by luojian on 2017/4/18.
 */
@Component
public class ArticleService {

    private final ManagerLogDao managerLogDao;
    private final ArticleDao articleDao;

    @Autowired
    public ArticleService(ArticleDao articleDao, ManagerLogDao managerLogDao) {
        this.articleDao = articleDao;
        this.managerLogDao = managerLogDao;
    }

    @Transactional
    public Article publishArticle(Manager manager, Article article) throws BeautyException {
        String uuid = UUID.randomUUID().toString();

        ManagerLog log = ManagerPrivilege.requestPermission(manager, ManagerPrivilege.MANAGER_PUBLISH_ARTICLE, "发布文章" + uuid);
        managerLogDao.save(log);

        article.setId(uuid);

        if (article.getPublishTime() == null) {
            article.setPublishTime(new Date(System.currentTimeMillis()));
        }
        article.setUpdateTime(new Date(System.currentTimeMillis()));
        article.setStatusCode(0);
        article.setStatusName("待审核");

        articleDao.save(article);

        return article;
    }

    public PageInfo<Article> listArticle(int draw, int start, int length, String keyword) {
        PageInfo<Article> pi = new PageInfo<Article>();
        int count = articleDao.count();
        pi.setDraw(draw);
        pi.setRecordsTotal(count);
        if (TextUtils.isEmpty(keyword)) {
            pi.setRecordsFiltered(count);
            pi.setData(articleDao.list(start, length));
        } else {
            pi.setRecordsFiltered(articleDao.count(keyword));
            pi.setData(articleDao.list(start, length, keyword));
        }
        return pi;
    }

    public Article viewById(String id) throws BeautyException {
        if (TextUtils.isEmpty(id)) {
            throw new BeautyException("找不到文章");
        }
        Article a = articleDao.getById(id);
        if (a == null) {
            throw new BeautyException("找不到文章");
        }
        if (a.getStatusCode() == 99) {
            throw new BeautyException("文章已删除");
        }
        return a;
    }

    @Transactional
    public void editArticle(Manager manager, Article article) throws BeautyException {
        ManagerLog log = ManagerPrivilege.requestPermission(manager, ManagerPrivilege.MANAGER_EDIT_ARTICLE, "修改文章" + article.getId());
        managerLogDao.save(log);
        article.setUpdateTime(new Date(System.currentTimeMillis()));
        articleDao.update(article);
    }

    public boolean exist(String title, String sourceUrl) {
        return articleDao.searchByTitleOrSourceUrl(title, sourceUrl) > 0;

    }
}
