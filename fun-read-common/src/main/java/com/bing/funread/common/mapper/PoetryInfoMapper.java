package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.PoetryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoetryInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PoetryInfo record);

    int insertSelective(PoetryInfo record);

    PoetryInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PoetryInfo record);

    int updateByPrimaryKey(PoetryInfo record);

    List<PoetryInfo> selectByPoetryId(@Param("poetryId")Long poetryId);
}