package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.UserCourse;
import com.bing.funread.common.dto.ReadInfoDto;
import org.apache.ibatis.annotations.Param;

public interface UserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourse record);

    int insertSelective(UserCourse record);

    UserCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCourse record);

    int updateByPrimaryKey(UserCourse record);

    UserCourse selectByUserAndCourse(@Param("userId")Long userId, @Param("courseId")Long courseId);

    int selectHasStudyPoetrys(Long userId);

    ReadInfoDto selectReadInfo(@Param("userId")Long userId, @Param("courseId")Long courseId, @Param("poetryId")Long poetryId);

    int updateCourseFinishedAddOne(Long id);
}