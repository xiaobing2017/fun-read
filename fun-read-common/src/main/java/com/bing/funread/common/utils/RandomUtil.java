package com.bing.funread.common.utils;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * Description:随机数工具类
 * Author: zhangfusheng
 * Date: 2018/3/14 下午2:43
 */
public class RandomUtil {

    public static final Random random = new Random();

    /**
     * 从list中随机抽取若干不重复元素
     * @param dataList
     * @param count
     * @return
     */
    public static <T> List<T> getRandomList(List<T> dataList, int count) {
        if (dataList == null || dataList.size() < count) {
            return dataList;
        }
        Random random = new Random();
        List<T> newList = Lists.newArrayList();
        for (int i = 0; i < count; i++) {
            int index = random.nextInt(dataList.size());
            T data = dataList.get(index);
            if (newList.contains(data)) {
                i--;
            } else {
                newList.add(data);
            }
        }
        return newList;
    }

    /**
     * 获取指定长度的数字、大小写字母组成的字符串
     * @param length
     * @return
     */
    public static String getRandom(int length) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < length; i++) {
            boolean isChar = random.nextInt(2) % 2 == 0;// 输出字母还是数字
            if (isChar) { // 字符串
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                ret.append((char) (choice + random.nextInt(26)));
            } else { // 数字
                ret.append(Integer.toString(random.nextInt(10)));
            }
        }
        return ret.toString();
    }
}
