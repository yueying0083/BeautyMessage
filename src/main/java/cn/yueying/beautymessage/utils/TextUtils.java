package cn.yueying.beautymessage.utils;

import java.util.Objects;

/**
 * Created by luojian on 2016/11/29.
 */
public class TextUtils {

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static int parseString2Int(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return -1;
        }
    }

}
