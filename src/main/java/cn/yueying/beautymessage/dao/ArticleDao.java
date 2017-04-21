package cn.yueying.beautymessage.dao;

import cn.yueying.beautymessage.model.Article;
import cn.yueying.beautymessage.model.PageInfo;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luojian on 2017/4/18.
 */
@Component
public class ArticleDao extends JdbcDaoSupport {

    public void save(Article article) {
        DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_MANAGER);
        String sql = "INSERT INTO beauty_message.article(id, title, sub_title, author, source_name, source_url, label, img_label_1, " +
                "img_label_2, img_label_3, type_code, type_name, content, publish_time, update_time, view_counts, reply_counts, status_code, status_name) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        getJdbcTemplate().update(sql, article.getId(), article.getTitle(), article.getSubTitle(), article.getAuthor(), article.getSourceName(),
                article.getSourceUrl(), article.getLabel(), article.getImgLabel1(), article.getImgLabel2(), article.getImgLabel3(),
                article.getTypeCode(), article.getTypeName(), article.getContent(), article.getPublishTime(), article.getUpdateTime(),
                article.getViewCounts(), article.getReplyCounts(), article.getStatusCode(), article.getStatusName());
    }

    public List<Article> list(int start, int length) {
        DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_MANAGER);
        String sql = "SELECT id, title, author, label, img_label_1, img_label_2, img_label_3, type_code, type_name, publish_time, status_code, status_name" +
                " FROM beauty_message.article ORDER BY publish_time DESC LIMIT ?, ?";

        return getJdbcTemplate().query(sql, new Object[]{start, length}, Article.s_managerRowMapper_1);
    }

    public int count() {
        String sql = "SELECT count(0) FROM beauty_message.article";
        return getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    public List<Article> list(int start, int length, String keyword) {
        DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_MANAGER);
        String sql = "SELECT id, title, author, label, img_label_1, img_label_2, img_label_3, type_code, type_name, publish_time, status_code, status_name" +
                " FROM beauty_message.article WHERE label LIKE ? ORDER BY update_time DESC LIMIT ?, ?";

        return getJdbcTemplate().query(sql, new Object[]{"%" + keyword + "%", start, length}, Article.s_managerRowMapper_1);
    }

    public int count(String keyword) {
        String sql = "SELECT count(0) FROM beauty_message.article WHERE label LIKE ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{"%" + keyword + "%"}, Integer.class);
    }

    public Article getById(String id) {
        String sql = "SELECT * FROM beauty_message.article WHERE id = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, Article.s_managerRowMapper_full);
    }

    public void update(Article article) {
        String sql = "UPDATE beauty_message.article SET title = ?, sub_title = ?, author = ?, source_name = ?, source_url = ?, label = ?" +
                ", img_label_1 = ?, img_label_2 = ?, img_label_3 = ?, type_code = ?, type_name = ?, content = ?, update_time = ? WHERE id = ?";
        getJdbcTemplate().update(sql, article.getTitle(), article.getSubTitle(), article.getAuthor(), article.getSourceName(),
                article.getSourceUrl(), article.getLabel(), article.getImgLabel1(), article.getImgLabel2(), article.getImgLabel3(), article.getTypeCode()
                , article.getTypeName(), article.getContent(), article.getUpdateTime(), article.getId());
    }

    public int searchByTitleOrSourceUrl(String title, String sourceUrl) {
        DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_MANAGER);
        String sql = "SELECT count(0) FROM beauty_message.article WHERE title = ? OR source_url = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{title, sourceUrl}, Integer.class);
    }
}
