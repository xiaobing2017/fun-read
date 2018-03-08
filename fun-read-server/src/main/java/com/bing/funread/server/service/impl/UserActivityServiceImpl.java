package com.bing.funread.server.service.impl;

import com.bing.funread.common.domain.Activity;
import com.bing.funread.common.domain.ActivityPoetry;
import com.bing.funread.common.domain.UserActivity;
import com.bing.funread.common.mapper.ActivityMapper;
import com.bing.funread.common.mapper.ActivityPoetryMapper;
import com.bing.funread.common.mapper.UserActivityMapper;
import com.bing.funread.response.UserActivityInfoVo;
import com.bing.funread.server.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:用户活动接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/8 下午3:39
 */
@Service
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private UserActivityMapper userActivityMapper;

    @Autowired
    private ActivityPoetryMapper activityPoetryMapper;

    @Override
    public UserActivityInfoVo getUserActivityInfo(Long userId, Long activityId) {
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        UserActivity userActivity = userActivityMapper.selectUserActivityInfo(userId, activityId);
        List<ActivityPoetry> activityPoetryList = activityPoetryMapper.selectByActivityId(activityId);

        UserActivityInfoVo activityInfoVo = new UserActivityInfoVo();
        activityInfoVo.setFinishNum(userActivity.getUnlockNum());
        activityInfoVo.setUnFinishNum(activity.getPoetryNum() - userActivity.getUnlockNum());
        activityInfoVo.setPoetryId(activityPoetryList.get(userActivity.getUnlockNum()).getPoetryId());
        return null;
    }
}
