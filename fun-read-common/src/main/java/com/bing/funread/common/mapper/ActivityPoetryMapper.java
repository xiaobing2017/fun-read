package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.ActivityPoetry;

public interface ActivityPoetryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityPoetry record);

    int insertSelective(ActivityPoetry record);

    ActivityPoetry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityPoetry record);

    int updateByPrimaryKey(ActivityPoetry record);
}