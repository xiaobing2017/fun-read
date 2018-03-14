package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.Exercises;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExercisesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Exercises record);

    int insertSelective(Exercises record);

    Exercises selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Exercises record);

    int updateByPrimaryKey(Exercises record);

    List<Exercises> selectByPoetryId(@Param("poetryId")Long poetryId);

    List<Long> selectCourseExercises(@Param("courseId")Long courseId);

    List<Exercises> selectByCourseIdList(@Param("courseIdList")List<Long> courseIdList);
}