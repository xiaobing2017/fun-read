package com.bing.funread.server.service;

import com.bing.funread.common.dto.WeChatLoginCheckDto;
import com.bing.funread.request.ShareCodeRequest;

/**
 * Description:微信接口
 * Author: zhangfusheng
 * Date: 2018/4/3 下午3:35
 */
public interface WeChatService {

    /**
     * 登录微信查询用户信息
     * @param code
     * @return
     */
    WeChatLoginCheckDto loginCheck(String code);

    /**
     * 获取公众号的全局唯一接口调用凭据
     * @return
     */
    String getAccessToken();

    /**
     * 获取小程序码
     * @param request
     * @return
     */
    String getShareCode(ShareCodeRequest request);
}
