package com.bing.funread.server.service.impl;

import com.bing.funread.common.domain.ShareLog;
import com.bing.funread.common.mapper.ShareLogMapper;
import com.bing.funread.common.utils.BeanUtil;
import com.bing.funread.common.utils.RandomUtil;
import com.bing.funread.request.ShareRequest;
import com.bing.funread.response.ShareVo;
import com.bing.funread.server.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:分享接口服务
 * Author: zhangfusheng
 * Date: 2018/4/3 下午8:05
 */
@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareLogMapper shareLogMapper;

    @Override
    public String getUniqueId(ShareRequest request) {
        ShareLog record = BeanUtil.copyBean(request, ShareLog.class);
        record.setUniqueId(RandomUtil.getRandom(10));
        shareLogMapper.insertSelective(record);
        return record.getUniqueId();
    }

    @Override
    public ShareVo getShareInfo(String uniqueId) {
        ShareLog record = shareLogMapper.selectByUniqueId(uniqueId);
        return BeanUtil.copyBean(record, ShareVo.class);
    }
}
