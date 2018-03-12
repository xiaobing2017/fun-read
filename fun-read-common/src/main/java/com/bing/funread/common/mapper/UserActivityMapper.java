package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.UserActivity;
import org.apache.ibatis.annotations.Param;

public interface UserActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserActivity record);

    int insertSelective(UserActivity record);

    UserActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserActivity record);

    int updateByPrimaryKey(UserActivity record);

    UserActivity selectUserActivityInfo(@Param("userId")Long userId, @Param("activityId")Long activityId);
}