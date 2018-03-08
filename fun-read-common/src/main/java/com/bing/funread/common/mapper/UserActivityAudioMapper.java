package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.UserActivityAudio;

public interface UserActivityAudioMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserActivityAudio record);

    int insertSelective(UserActivityAudio record);

    UserActivityAudio selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserActivityAudio record);

    int updateByPrimaryKey(UserActivityAudio record);
}