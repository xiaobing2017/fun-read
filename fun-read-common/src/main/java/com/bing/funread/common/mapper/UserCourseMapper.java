package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.UserCourse;
import com.bing.funread.common.dto.CourseUserNumDto;
import com.bing.funread.common.dto.ReadInfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCourse record);

    int insertSelective(UserCourse record);

    UserCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCourse record);

    int updateByPrimaryKey(UserCourse record);

    UserCourse selectByUserAndCourse(@Param("userId")Long userId, @Param("courseId")Long courseId);

    int selectHasStudyPoetrys(@Param("userId")Long userId);

    List<ReadInfoDto> selectReadInfo(@Param("userId")Long userId, @Param("courseId")Long courseId, @Param("poetryId")Long poetryId);

    int updateCourseFinishedNum(Long id);

    List<CourseUserNumDto> selectUserNumByCourseIdList(@Param("courseIdList")List<Long> courseIdList);

    int updateUserIdByPhone(UserCourse record);
}