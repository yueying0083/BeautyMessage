package cn.yueying.beautymessage.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by luojian on 2017/4/18.
 */
public class Article implements java.io.Serializable {

    private static final long serialVersionUID = -7589773578892668657L;
    private String id;// uuid
    private String title;// 标题
    private String subTitle;// 小标题

    private String author;// 作者
    private String sourceName;// 来源
    private String sourceUrl;// 来源网站

    private String label;// 标签
    private String imgLabel1;// 图片标签1
    private String imgLabel2;// 图片标签2
    private String imgLabel3;// 图片标签3

    private int typeCode;// 类型
    private String typeName;// 类型名

    private String content;// 内容
    private Date publishTime;// 发布时间
    private Date updateTime;// 更新时间

    private int viewCounts;// 查看数量
    private int replyCounts;// 回复数量

    private int statusCode;
    private String statusName;

    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
    private String remark5;
    private String remark6;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImgLabel1() {
        return imgLabel1;
    }

    public void setImgLabel1(String imgLabel1) {
        this.imgLabel1 = imgLabel1;
    }

    public String getImgLabel2() {
        return imgLabel2;
    }

    public void setImgLabel2(String imgLabel2) {
        this.imgLabel2 = imgLabel2;
    }

    public String getImgLabel3() {
        return imgLabel3;
    }

    public void setImgLabel3(String imgLabel3) {
        this.imgLabel3 = imgLabel3;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getViewCounts() {
        return viewCounts;
    }

    public void setViewCounts(int viewCounts) {
        this.viewCounts = viewCounts;
    }

    public int getReplyCounts() {
        return replyCounts;
    }

    public void setReplyCounts(int replyCounts) {
        this.replyCounts = replyCounts;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    public String getRemark5() {
        return remark5;
    }

    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }

    public String getRemark6() {
        return remark6;
    }

    public void setRemark6(String remark6) {
        this.remark6 = remark6;
    }

    public static final RowMapper<Article> s_managerRowMapper_1 = new RowMapper<Article>() {

        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article m = new Article();

            m.id = rs.getString("id");
            m.title = rs.getString("title");
            // m.subTitle = rs.getString("sub_title");
            m.author = rs.getString("author");
            // m.sourceName = rs.getString("source_name");
            // m.sourceUrl = rs.getString("source_url");
            m.label = rs.getString("label");
            m.imgLabel1 = rs.getString("img_label_1");
            m.imgLabel2 = rs.getString("img_label_2");
            m.imgLabel3 = rs.getString("img_label_3");
            m.typeCode = rs.getInt("type_code");
            m.typeName = rs.getString("type_name");
            // m.content = rs.getString("content");
            m.publishTime = rs.getDate("publish_time");
            // m.updateTime = rs.getDate("update_time");
            // m.viewCounts = rs.getInt("view_counts");
            // m.replyCounts = rs.getInt("reply_counts");
            m.statusCode = rs.getInt("status_code");
            m.statusName = rs.getString("status_Name");
            // m.remark1 = rs.getString("remark1");
            // m.remark2 = rs.getString("remark2");
            // m.remark3 = rs.getString("remark3");
            // m.remark4 = rs.getString("remark4");
            // m.remark5 = rs.getString("remark5");
            // m.remark6 = rs.getString("remark6");
            return m;
        }
    };

    public static final RowMapper<Article> s_managerRowMapper_full = new RowMapper<Article>() {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article m = new Article();

            m.id = rs.getString("id");
            m.title = rs.getString("title");
            m.subTitle = rs.getString("sub_title");
            m.author = rs.getString("author");
            m.sourceName = rs.getString("source_name");
            m.sourceUrl = rs.getString("source_url");
            m.label = rs.getString("label");
            m.imgLabel1 = rs.getString("img_label_1");
            m.imgLabel2 = rs.getString("img_label_2");
            m.imgLabel3 = rs.getString("img_label_3");
            m.typeCode = rs.getInt("type_code");
            m.typeName = rs.getString("type_name");
            m.content = rs.getString("content");
            m.publishTime = rs.getDate("publish_time");
            m.updateTime = rs.getDate("update_time");
            m.viewCounts = rs.getInt("view_counts");
            m.replyCounts = rs.getInt("reply_counts");
            m.statusCode = rs.getInt("status_code");
            m.statusName = rs.getString("status_Name");
            m.remark1 = rs.getString("remark1");
            m.remark2 = rs.getString("remark2");
            m.remark3 = rs.getString("remark3");
            m.remark4 = rs.getString("remark4");
            m.remark5 = rs.getString("remark5");
            m.remark6 = rs.getString("remark6");
            return m;
        }
    };


}
