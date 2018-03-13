package com.bing.funread.server.service;

import com.bing.funread.response.ActivityVo;

/**
 * Description:活动接口
 * Author: zhangfusheng
 * Date: 2018/3/8 下午2:48
 */
public interface ActivityService {

    /**
     * 查询活动介绍
     * @param activityId
     * @return
     */
    ActivityVo getActivityInfo(Long activityId);
}
