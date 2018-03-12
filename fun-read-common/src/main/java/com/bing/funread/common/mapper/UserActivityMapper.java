package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.UserActivity;
import com.bing.funread.common.dto.ActivityInfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserActivity record);

    int insertSelective(UserActivity record);

    UserActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserActivity record);

    int updateByPrimaryKey(UserActivity record);

    UserActivity selectUserActivityInfo(@Param("userId")Long userId, @Param("activityId")Long activityId);

    int updateActivityFinishedNum(Long id);

    List<ActivityInfoDto> selectActivityInfo(@Param("userId")Long userId);

    List<Long> selectUserActivityId(@Param("userId")Long userId);
}