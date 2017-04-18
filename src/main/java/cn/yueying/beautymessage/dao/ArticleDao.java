package cn.yueying.beautymessage.dao;

import cn.yueying.beautymessage.model.Article;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

/**
 * Created by luojian on 2017/4/18.
 */
@Component
public class ArticleDao extends JdbcDaoSupport {

    public void save(Article article) {
        String sql = "INSERT INTO beauty_message.article(id, title, sub_title, author, source_name, source_url, label, img_label_1, " +
                "image_label_2, img_label_3, type_code, type_name, content, publish_time, update_time, view_counts, reply_counts) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        getJdbcTemplate().update(sql, article.getId(), article.getTitle(), article.getSubTitle(), article.getAuthor(), article.getSourceName(),
                article.getSourceUrl(), article.getLabel(), article.getImgLabel1(), article.getImgLabel2(), article.getImgLabel3(),
                article.getTypeCode(), article.getTypeName(), article.getContent(), article.getPublishTime(), article.getUpdateTime(),
                article.getViewCounts(), article.getReplyCounts());
    }


}
