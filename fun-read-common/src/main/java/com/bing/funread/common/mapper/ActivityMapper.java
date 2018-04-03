package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.Activity;
import com.bing.funread.common.dto.ActivityUserNumDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<ActivityUserNumDto> selectActivityUserNum();

    /**
     * 查询已完成的任务
     * @param userId
     * @return
     */
    List<Activity> selectFinishedActivity(@Param("userId")Long userId);
}