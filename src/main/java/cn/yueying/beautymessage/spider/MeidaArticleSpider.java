package cn.yueying.beautymessage.spider;

import cn.yueying.beautymessage.exception.BeautyException;
import cn.yueying.beautymessage.model.Article;
import cn.yueying.beautymessage.service.ArticleService;
import cn.yueying.beautymessage.service.ManageService;
import cn.yueying.beautymessage.utils.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 美搭网（zzmmgo.com）数据抓取
 * Created by luojian on 2017/4/27.
 */
public class MeidaArticleSpider extends AbsSpider<Article> {

    private Logger logger = LoggerFactory.getLogger(MeidaArticleSpider.class);
    private static final String BASE_URL = "http://www.zzmmgo.com";

    private final ManageService manageService;
    private final ArticleService articleService;

    @Autowired
    protected MeidaArticleSpider(ArticleService articleService, ManageService manageService) {
        this.articleService = articleService;
        this.manageService = manageService;
    }

    @Override
    protected Article spiderOne(String url) {
        try {
            Article article = null;
            Document document = Jsoup.parse(new URL(url), 30000);
            Elements els = document.getElementsByClass("fashion_left");
            if (els != null && els.size() == 1) {
                article = new Article();
                article.setSourceName("美搭网");
                article.setSourceUrl(url);
                Element e = els.get(0);
                Element title = getSingleElementByTagName(e, "h1");
                article.setTitle(title == null ? null : title.html());

                Element author = getSingleElementByClassName(e, "eye");
                if (author != null) {
                    String text = author.html().replaceAll("&nbsp;", "");
                    if (text != null) {
                        int i_editor = text.indexOf("编辑：");
                        int i_time = text.indexOf("时间：");

                        if (i_editor > -1 && i_time > -1 && i_editor < i_time) {
                            // 作者
                            article.setAuthor(text.substring(i_editor + 3, i_time).trim());

                            // 发布时间
                            try {
                                article.setPublishTime(new SimpleDateFormat("yyyy-MM-dd").parse(text.substring(i_time + 3).trim()));
                            } catch (Exception ee) {
                                logger.error("获取文章发布时间出错", ee);
                            }
                        }
                    }
                }

                Element content = getSingleElementByClassName(e, "fashion_left_content");
                if (content != null) {

                    // 正文
                    Elements contents = content.getAllElements();
                    List<Element> removeContent = new ArrayList<Element>();
                    if (contents != null) {
                        for (Element ee : contents) {
                            if (ee.getElementsByTag("a").size() != 0) {
                                removeContent.addAll(ee.getElementsByTag("a"));
                            }
                        }
                        contents.removeAll(removeContent);
                        String s_content = contents.toString().replaceAll("\n\r", "");
                        s_content = s_content.replaceAll("\r\n", "");
                        s_content = s_content.replaceAll("\n", "");
                        article.setContent(s_content);
                    }

                    // 图片标签
                    Elements imgs = content.getElementsByTag("img");
                    if (imgs != null) {
                        for (int i = 0, l = Math.min(imgs.size(), 3); i < l; i++) {
                            String img_url = imgs.get(i).attr("src");
                            if (!TextUtils.isEmpty(img_url)) {
                                if (!img_url.startsWith("http")) {
                                    if (img_url.startsWith("/")) {
                                        String headUrl = content.baseUri();
                                        if (headUrl.startsWith("http://")) {
                                            headUrl = headUrl.substring(7);
                                            headUrl = headUrl.substring(0, headUrl.indexOf("/"));
                                            img_url = "http://" + headUrl + img_url;
                                        } else if (headUrl.startsWith("https://")) {
                                            headUrl = headUrl.substring(8);
                                            headUrl = headUrl.substring(0, headUrl.indexOf("/"));
                                            img_url = "https://" + headUrl + img_url;
                                        }
                                    } else {
                                        img_url = url.substring(0, url.lastIndexOf("/") + 1) + img_url;
                                    }
                                }

                                if (i == 0) {
                                    article.setImgLabel1(img_url);
                                } else if (i == 1) {
                                    article.setImgLabel2(img_url);
                                } else {
                                    article.setImgLabel3(img_url);
                                }
                            }
                        }
                    }
                }
            } else {
                logger.error("文章内容出现多个内容区域");
            }

            boolean legal = article != null;
            legal = legal && !TextUtils.isEmpty(article.getTitle());
            legal = legal && !TextUtils.isEmpty(article.getContent());
            return legal ? article : null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected String getFirstMenuUrl() {
        return BASE_URL + "/dress/";
    }

    @Override
    protected MenuModel getMenu(String menuUrl) {
        try {
            MenuModel mm = new MenuModel();
            Document document = Jsoup.parse(new URL(menuUrl), 30000);
            Elements elements = document.getElementsByClass("fashion_list_text");
            if (!elements.isEmpty()) {
                List<String> list = new ArrayList<String>();
                for (Element element : elements) {
                    String str = null;
                    if (element != null) {
                        Elements els = element.getElementsByTag("a");
                        if (els.size() >= 1) {
                            str = els.get(0).attr("href");
                            if (str != null && !"".equalsIgnoreCase(str)) {
                                if (!str.startsWith("http")) {
                                    str = BASE_URL + str;
                                }
                            }
                        }
                    }
                    if (str == null || "".equalsIgnoreCase(str)) {
                        logger.error("抓文章链接出现空值");
                        break;
                    }
                    list.add(str);
                }
                mm.settUrlList(list);
            }

            Elements load_more = document.getElementsByClass("a1");
            if (load_more.size() == 3) {
                String str = load_more.get(2).attr("href");
                if (str != null && !"".equalsIgnoreCase(str)) {
                    if (!str.startsWith("http")) {
                        str = BASE_URL + str;
                    }
                    if(!menuUrl.equalsIgnoreCase(str)) {
                        mm.setNextPage(str);
                    }
                }
            }
            return mm;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void save(Article article) {
        try {
            logger.debug("保存文章 title = {}", article.getTitle());
            articleService.publishArticle(manageService.getSpiderUser(), article);
        } catch (BeautyException e) {
            logger.error("抓取文章保存出现错误", e);
        }
    }

    @Override
    protected boolean exist(Article article) {
        return articleService.exist(article.getTitle(), article.getSourceUrl());
    }
}
