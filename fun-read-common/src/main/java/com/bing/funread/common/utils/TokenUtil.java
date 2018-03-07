package com.bing.funread.common.utils;

import com.bing.funread.common.domain.User;

/**
 * Description:token处理工具类
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:43
 */
public class TokenUtil {

    public static String createToken(User user) {

        return user.getUnionId();
    }
}
