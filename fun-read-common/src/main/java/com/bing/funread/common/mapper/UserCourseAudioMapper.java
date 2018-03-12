package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.UserCourseAudio;

public interface UserCourseAudioMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourseAudio record);

    int insertSelective(UserCourseAudio record);

    UserCourseAudio selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCourseAudio record);

    int updateByPrimaryKey(UserCourseAudio record);

    int insertOrUpdateSelective(UserCourseAudio record);
}