package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.Poetry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoetryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Poetry record);

    int insertSelective(Poetry record);

    Poetry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Poetry record);

    int updateByPrimaryKey(Poetry record);

    List<Poetry> selectCoursePoetryInfo(@Param("courseId")Long courseId);
}