package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.ShareLog;
import org.apache.ibatis.annotations.Param;

public interface ShareLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShareLog record);

    int insertSelective(ShareLog record);

    ShareLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShareLog record);

    int updateByPrimaryKey(ShareLog record);

    ShareLog selectByUniqueId(@Param("uniqueId") String uniqueId);
}