package com.bing.funread.server.service;

import com.bing.funread.common.domain.User;
import com.bing.funread.request.PageRequest;
import com.bing.funread.request.UserInfoRequest;
import com.bing.funread.request.WeChatLoginRequest;
import com.bing.funread.response.AwardInfoVo;
import com.bing.funread.response.UserInfoVo;
import com.bing.funread.response.UserStudyInfoVo;

/**
 * Description:用户接口
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:24
 */
public interface UserService {

    /**
     * 微信授权登录
     * @param login
     * @return
     */
    User login(WeChatLoginRequest login);

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

    /**
     * 绑定手机号
     * @param userId
     * @param phone
     */
    boolean bindPhone(Long userId, String phone);

    /**
     * 查询用户学习情况
     * @param userId
     * @return
     */
    UserStudyInfoVo getUserStudyDetail(Long userId);

    /**
     * 查询奖状信息
     * @param userId
     * @param pageRequest
     * @return
     */
    AwardInfoVo getAward(Long userId, PageRequest pageRequest);
}
