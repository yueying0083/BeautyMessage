package cn.yueying.beautymessage.utils;

import cn.yueying.beautymessage.model.Article;

/**
 * Created by luojian on 2017/4/21.
 */
public class TagUtils {

    private static final String[] TAGS = {"搭配", "小清新", "明星", "时尚", "复古", "朋克", "美妆"};

    public static void setTag(Article article) {
        if (article == null || TextUtils.isEmpty(article.getContent())) {
            return;
        }

        String content = article.getContent();
        int i = 0;
        for (String s : TAGS) {
            if (content.contains(s)) {
                String label = article.getLabel();
                article.setLabel(TextUtils.isEmpty(label) ? s : label + "," + s);
                if (i++ > 5) {
                    break;
                }
            }
        }
    }
}
