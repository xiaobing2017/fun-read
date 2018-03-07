package com.bing.funread.server.service.impl;

import com.bing.funread.common.domain.PoetryInfo;
import com.bing.funread.common.mapper.PoetryInfoMapper;
import com.bing.funread.common.utils.BeanUtil;
import com.bing.funread.response.PoetryInfoVo;
import com.bing.funread.server.service.PoetryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:诗句接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/7 下午8:35
 */
@Service
public class PoetryInfoServiceImpl implements PoetryInfoService {

    @Autowired
    private PoetryInfoMapper poetryInfoMapper;

    @Override
    public List<PoetryInfoVo> getPoetryInfo(Long poetryId) {
        List<PoetryInfo> poetryInfoList = poetryInfoMapper.selectByPoetryId(poetryId);
        return BeanUtil.copyList(poetryInfoList, PoetryInfoVo.class);
    }
}
