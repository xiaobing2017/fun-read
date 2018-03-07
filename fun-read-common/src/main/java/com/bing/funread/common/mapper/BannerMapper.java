package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.Banner;

import java.util.List;

public interface BannerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

    List<Banner> selectAllShowBanners();
}