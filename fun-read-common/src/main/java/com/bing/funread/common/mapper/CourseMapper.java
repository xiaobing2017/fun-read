package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.Course;
import com.bing.funread.common.dto.CourseInfoDto;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    /**
     * 查询课程列表
     * @return
     */
    List<CourseInfoDto> selectAllCourseInfo();
}