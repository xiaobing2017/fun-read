package com.bing.funread.server.controller;

import com.bing.funread.common.constants.CommonConstant;
import com.bing.funread.common.utils.TokenUtil;

/**
 * Description:通用接口服务
 * Author: zhangfusheng
 * Date: 2018/3/11 上午11:48
 */
abstract class BaseController {

    Long getUserId() {
        return ((Integer) TokenUtil.get(CommonConstant.TOKEN_USER_ID)).longValue();
    }
}
