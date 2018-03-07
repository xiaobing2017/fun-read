package com.bing.funread.server.service;

import com.bing.funread.response.HomeBannerVo;

import java.util.List;

/**
 * Description:banner图接口
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:53
 */
public interface BannerService {

    /**
     * 查询首页banner图信息
     * @return
     */
    List<HomeBannerVo> getBanner();
}
