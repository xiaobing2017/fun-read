package com.bing.funread.server.service.impl;

import com.bing.funread.common.domain.Activity;
import com.bing.funread.common.mapper.ActivityMapper;
import com.bing.funread.common.utils.BeanUtil;
import com.bing.funread.response.ActivityVo;
import com.bing.funread.server.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:活动接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/8 下午2:48
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ActivityVo getActivityInfo(Long activityId) {
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        return BeanUtil.copyBean(activity, ActivityVo.class);
    }
}
