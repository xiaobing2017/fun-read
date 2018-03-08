package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.UserCourse;

public interface UserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourse record);

    int insertSelective(UserCourse record);

    UserCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCourse record);

    int updateByPrimaryKey(UserCourse record);

    UserCourse selectByUserAndCourse(Long userId, Long courseId);

    int selectHasStudyPoetrys(Long userId);
}