package cn.yueying.beautymessage.spider;

import cn.yueying.beautymessage.utils.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by luojian on 2017/4/21.
 */
public abstract class AbsSpider<T> {

    private Logger logger = LoggerFactory.getLogger(AbsSpider.class);

    protected abstract T spiderOne(String url);

    protected abstract String getFirstMenuUrl();

    protected abstract MenuModel getMenu(String menuUrl);

    protected abstract void save(T t);

    protected abstract boolean exist(T t);

    public void start() {
        logger.debug("start spider =====");
        int i = 0;
        String url = getFirstMenuUrl();
        while(!TextUtils.isEmpty(url)) {
            i++;
            MenuModel menuModel = getMenu(url);
            if (menuModel == null) {
                logger.debug("第{}页面获取失败", i);
                break;
            }
            url = menuModel.getNextPage();

            logger.debug("第{}个页面获取成功", i);
            List<String> list = menuModel.gettUrlList();
            if (list == null || list.isEmpty()) {
                logger.debug("第{}页没有文章数据，退出", i);
                break;
            }
            int j = 0;
            boolean exist = false;
            for (String t_url : list) {
                j++;
                T t = spiderOne(t_url);
                if (t == null) {
                    logger.debug("第{}页第{}篇抓取失败，抓取下一篇", i, j);
                    continue;
                }
                if (exist(t)) {
                    exist = true;
                    break;
                }
                save(t);
            }
            if (exist) {
                logger.debug("第{}页第{}篇已存在，停止抓取", i, j);
                break;
            }
        }
        logger.debug("end spider =====");
    }

    public static class MenuModel {
        private List<String> tUrlList;
        private String nextPage;

        public List<String> gettUrlList() {
            return tUrlList;
        }

        public void settUrlList(List<String> tUrlList) {
            this.tUrlList = tUrlList;
        }

        public String getNextPage() {
            return nextPage;
        }

        public void setNextPage(String nextPage) {
            this.nextPage = nextPage;
        }
    }


}
