package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.ExercisesOptions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExercisesOptionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExercisesOptions record);

    int insertSelective(ExercisesOptions record);

    ExercisesOptions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExercisesOptions record);

    int updateByPrimaryKey(ExercisesOptions record);

    List<ExercisesOptions> selectByPoetryId(@Param("poetryId")Long poetryId);

    List<ExercisesOptions> selectByCourseIdList(@Param("courseIdList")List<Long> courseIdList);
}