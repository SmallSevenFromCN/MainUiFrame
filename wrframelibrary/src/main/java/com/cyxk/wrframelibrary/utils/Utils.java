package com.cyxk.wrframelibrary.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;


import com.cyxk.wrframelibrary.base.APP;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 *
 * @author biezhi
 *         17/06/2017
 */
public final class Utils {



    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    private Utils() {
    }

    /**
     * 按顺序创建一个Map
     *
     * @param values
     * @return
     */
    public static Map<String, Object> createMap(Object... values) {
        Map<String, Object> map = new HashMap<String, Object>(values.length / 2);
        for (int i = 0; i < values.length; i++) {
            map.put(values[i].toString(), values[++i]);
        }
        return map;
    }

    /**
     * 当str1为null或者空则使用str2
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String emptyOr(String str1, String str2) {
        if (isBlank(str1)) {
            return str2;
        }
        return str1;
    }

    /**
     * 让JVM休眠timeout毫秒
     *
     * @param timeout
     */
    public static void sleep(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (Exception e) {

        }
    }

    /**
     * 正则匹配
     *
     * @param p
     * @param str
     * @return
     */
    public static String match(String p, String str) {
        Pattern pattern = Pattern.compile(p);
        Matcher m = pattern.matcher(str);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    /**
     * 关闭Closeable
     *
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断str是否为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return null == str || "".equals(str.trim());
    }

    /**
     * 判断str是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str) && !"null".equalsIgnoreCase(str);
    }

    /**
     * 获取size个随机字符
     *
     * @param size
     * @return
     */
    public static String getRandomNumber(int size) {
        String num = "";
        for (int i = 0; i < size; i++) {
            double a = Math.random() * 9;
            a = Math.ceil(a);
            int randomNum = new Double(a).intValue();
            num += randomNum;
        }
        return num;
    }

    /**
     * obj转json
     */
    public static String toJson(Object o) {
        try {
            return gson.toJson(o);
        } catch (Exception e) {
            LogUtil.e("Json序列化失败"+e);
        }
        return null;
    }

    /**
     * json转obj
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            LogUtil.e("Json反序列化失败"+ e);
        }
        return null;
    }

    /**
     * Unicode字符串转为UTF8
     *
     * @param str
     * @return
     */
    public static String unicodeToUtf8(String str) {
        try {
            if (null == str) {
                return str;
            }
            if (str.indexOf("\\u") == -1) {
                return str;
            }
            byte[] utf8 = str.getBytes("UTF-8");
            // Convert from UTF-8 to Unicode
            str = new String(utf8, "UTF-8");
            return str;
        } catch (UnsupportedEncodingException e) {
        }
        return str;
    }

    /**
     * 将List Cookie转为String
     *
     * @param cookies
     * @return
     */
    public static String getCookie(List<String> cookies) {
        StringBuilder sBuffer = new StringBuilder();
        for (String value : cookies) {
            if (value == null) {
                continue;
            }
            String cookie = value.substring(0, value.indexOf(";") + 1);
            sBuffer.append(cookie);
        }
        return sBuffer.toString();
    }


    public static String getRandomChar(int length) {
        char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(62)]);
        }
        return buffer.toString();
    }
    public static String getRandomChar() {
        return getRandomChar(6);
    }


    public static void setVisibility1(View view){
        if (view.getVisibility() == View.GONE){
            view.setVisibility(View.VISIBLE);
        }
    }
    public static void setVisibility2(View view){
        if (view.getVisibility() == View.INVISIBLE){
            view.setVisibility(View.VISIBLE);
        }
    }
    public static void setInvisible(View view){
        if (view.getVisibility() == View.VISIBLE){
            view.setVisibility(View.INVISIBLE);
        }
    }
    public static void setGone(View view){
        if (view.getVisibility() == View.VISIBLE){
            view.setVisibility(View.GONE);
        }
    }

    public static void setBackGroundColor(View view, int color ) {
            view.setBackgroundColor(APP.getContext().getResources().getColor(color));
    }
    public static void setBackGroundDrawable(View view, int drawable ) {
        view.setBackgroundDrawable(APP.getContext().getResources().getDrawable(drawable));
    }

    public static void setSrc(ImageView imgRelease, int resource) {
        imgRelease.setImageDrawable(imgRelease.getContext().getResources().getDrawable(resource));
    }
}
