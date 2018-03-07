package com.bing.funread.server.service;

import com.bing.funread.response.PoetryInfoVo;

import java.util.List;

/**
 * Description:诗句接口
 * Author: zhangfusheng
 * Date: 2018/3/7 下午8:35
 */
public interface PoetryInfoService {

    /**
     * 根据诗词ID查询诗句信息
     * @param poetryId
     * @return
     */
    List<PoetryInfoVo> getPoetryInfo(Long poetryId);
}
