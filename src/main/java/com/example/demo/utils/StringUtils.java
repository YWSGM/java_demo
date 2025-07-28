package com.example.demo.utils;

/**
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * @param str 待检查的字符串
     * @return 如果字符串为null或空字符串，则返回true；否则返回false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 判断字符串是否不为空
     * @param str 待检查的字符串
     * @return 如果字符串不为null且不为空字符串，则返回true；否则返回false
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空白
     * @param str 待检查的字符串
     * @return 如果字符串为null、空字符串或仅包含空白字符，则返回true；否则返回false
     */
    public static boolean isBlank(String str) {
        if (isEmpty(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空白
     * @param str 待检查的字符串
     * @return 如果字符串不为null、不为空字符串且不仅包含空白字符，则返回true；否则返回false
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 截取字符串
     * @param str 原字符串
     * @param maxLength 最大长度
     * @return 截取后的字符串，如果原字符串长度小于等于最大长度，则返回原字符串
     */
    public static String truncate(String str, int maxLength) {
        if (isEmpty(str) || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength);
    }

    /**
     * 首字母大写
     * @param str 原字符串
     * @return 首字母大写后的字符串
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 首字母小写
     * @param str 原字符串
     * @return 首字母小写后的字符串
     */
    public static String uncapitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }
}

