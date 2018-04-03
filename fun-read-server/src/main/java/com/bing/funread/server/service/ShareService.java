package com.bing.funread.server.service;

import com.bing.funread.request.ShareRequest;
import com.bing.funread.response.ShareVo;

/**
 * Description:分享接口
 * Author: zhangfusheng
 * Date: 2018/4/3 下午8:05
 */
public interface ShareService {

    /**
     * 获取分享页面唯一ID
     * @param request
     * @return
     */
    String getUniqueId(ShareRequest request);

    /**
     * 查询分享参数
     * @param uniqueId
     * @return
     */
    ShareVo getShareInfo(String uniqueId);
}
