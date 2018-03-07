package com.bing.funread.common.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Description:
 * Author: zhangfusheng
 * Date: 2017/8/31 上午10:52
 */
public class BeanUtil {

    /**
     * 复制Bean
     * @param sourceObject
     * @param targetClass
     * @return
     */
    public static <T> T copyBean(Object sourceObject, Class<T> targetClass) {
        T targetObject = BeanUtils.instantiate(targetClass);
        BeanUtils.copyProperties(sourceObject, targetObject);
        return targetObject;
    }

    /**
     * 复制List
     * @param sourceList
     * @param targetClass
     * @return
     */
    public static <T> List<T> copyList(List sourceList, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return sourceList;
        }
        List<T> targetList = Lists.newArrayList();
        sourceList.forEach(source -> targetList.add(copyBean(source, targetClass)));
        return targetList;
    }
}
