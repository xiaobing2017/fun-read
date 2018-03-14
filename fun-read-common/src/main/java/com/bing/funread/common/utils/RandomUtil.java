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
}
