package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.ActivityPoetry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityPoetryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityPoetry record);

    int insertSelective(ActivityPoetry record);

    ActivityPoetry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityPoetry record);

    int updateByPrimaryKey(ActivityPoetry record);

    List<Long> selectPoetryIdByActivityId(@Param("activityId")Long activityId);

    ActivityPoetry selectByActivityAndPoetry(@Param("activityId")Long activityId, @Param("poetryId")Long poetryId);
}