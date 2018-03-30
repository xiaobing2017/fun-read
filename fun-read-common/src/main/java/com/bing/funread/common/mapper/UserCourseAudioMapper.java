package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.UserCourseAudio;
import org.apache.ibatis.annotations.Param;

public interface UserCourseAudioMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourseAudio record);

    int insertSelective(UserCourseAudio record);

    UserCourseAudio selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCourseAudio record);

    int updateByPrimaryKey(UserCourseAudio record);

    UserCourseAudio selectByUniqueKey(@Param("userCourseId") Long userCourseId, @Param("poetryId") Long poetryId, @Param("poetryInfoId") Long poetryInfoId);
}