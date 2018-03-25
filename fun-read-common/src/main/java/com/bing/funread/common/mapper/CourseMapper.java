package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.Course;
import com.bing.funread.common.dto.UserCourseInfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    /**
     * 查询用户课程
     * @param userId
     * @return
     */
    List<UserCourseInfoDto> selectUserCourseInfo(@Param("userId")Long userId);

    /**
     * 查询用户已完成课程
     * @param userId
     * @return
     */
    List<Course> selectFinishedCourse(@Param("userId")Long userId);
}