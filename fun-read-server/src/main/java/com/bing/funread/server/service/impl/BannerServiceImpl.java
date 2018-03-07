package com.bing.funread.server.service.impl;

import com.bing.funread.common.domain.Banner;
import com.bing.funread.common.mapper.BannerMapper;
import com.bing.funread.common.utils.BeanUtil;
import com.bing.funread.response.HomeBannerVo;
import com.bing.funread.server.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:banner图接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:59
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<HomeBannerVo> getBanner() {
        List<Banner> bannerList = bannerMapper.selectAllShowBanners();
        return BeanUtil.copyList(bannerList, HomeBannerVo.class);
    }
}
