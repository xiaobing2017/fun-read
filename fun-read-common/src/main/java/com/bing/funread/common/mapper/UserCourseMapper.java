package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.UserCourse;
import com.bing.funread.common.dto.ReadInfoDto;

public interface UserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourse record);

    int insertSelective(UserCourse record);

    UserCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCourse record);

    int updateByPrimaryKey(UserCourse record);

    UserCourse selectByUserAndCourse(Long userId, Long courseId);

    int selectHasStudyPoetrys(Long userId);

    ReadInfoDto selectReadInfo(Long userId, Long courseId, Long poetryId);

    int updateCourseFinishedAddOne(Long id);
}