package com.bing.funread.server.service;

import com.bing.funread.common.domain.User;
import com.bing.funread.request.WeChatRegRequest;

/**
 * Description:用户接口
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:24
 */
public interface UserService {

    /**
     * 微信用户注册
     * @param request
     * @return
     */
    User weChatRegister(WeChatRegRequest request);
}
