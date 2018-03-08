package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.UserActivity;

public interface UserActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserActivity record);

    int insertSelective(UserActivity record);

    UserActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserActivity record);

    int updateByPrimaryKey(UserActivity record);

    UserActivity selectUserActivityInfo(Long userId, Long activityId);
}