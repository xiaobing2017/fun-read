package com.bing.funread.server.service;

import com.bing.funread.common.domain.User;
import com.bing.funread.request.UserInfoRequest;
import com.bing.funread.request.WeChatRegRequest;
import com.bing.funread.response.UserInfoVo;

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

    /**
     * 查询用户资料
     * @param userId
     * @return
     */
    UserInfoVo getUserInfo(Long userId);

    /**
     * 更新用户资料
     * @param userId
     * @param userInfo
     */
    void updateUserInfo(Long userId, UserInfoRequest userInfo);
}
