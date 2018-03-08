package com.bing.funread.server.service;

import com.bing.funread.response.UserActivityInfoVo;

/**
 * Description:用户活动接口
 * Author: zhangfusheng
 * Date: 2018/3/8 下午3:39
 */
public interface UserActivityService {

    /**
     * 查询用户活动信息
     * @param userId
     * @param activityId
     * @return
     */
    UserActivityInfoVo getUserActivityInfo(Long userId, Long activityId);
}
