package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.CoursePoetry;
import org.apache.ibatis.annotations.Param;

public interface CoursePoetryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CoursePoetry record);

    int insertSelective(CoursePoetry record);

    CoursePoetry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursePoetry record);

    int updateByPrimaryKey(CoursePoetry record);

    CoursePoetry selectByCourseAndPoetry(@Param("courseId")Long courseId, @Param("poetryId")Long poetryId);
}