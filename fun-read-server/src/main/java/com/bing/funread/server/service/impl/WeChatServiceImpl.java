package com.bing.funread.server.service.impl;

import com.bing.funread.common.dto.WeChatAccessTokenDto;
import com.bing.funread.common.dto.WeChatLoginCheckDto;
import com.bing.funread.common.exception.ServiceException;
import com.bing.funread.common.utils.DateUtil;
import com.bing.funread.common.utils.HttpUtil;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.WeChatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description:微信接口服务类
 * Author: zhangfusheng
 * Date: 2018/4/3 下午3:36
 */
@Service
public class WeChatServiceImpl implements WeChatService {

    @Value("${weChat.app.id}")
    private String appId;

    @Value("${weChat.app.secret}")
    private String appSecret;

    @Value("${weChat.login.check.url}")
    private String checkUrl;

    @Value("${weChat.access.token.url}")
    private String tokenUrl;

    /**
     * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183
     * access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。
     */
    private static WeChatAccessTokenDto accessTokenDto;

    private static long cacheTime;

    @Override
    public WeChatLoginCheckDto loginCheck(String code) {
        String url = String.format(checkUrl, appId, appSecret, code);
        WeChatLoginCheckDto checkResult = HttpUtil.httpsRequest(url, RequestMethod.GET.name(), null, WeChatLoginCheckDto.class);
        if (checkResult == null || StringUtils.isBlank(checkResult.getSession_key())) {
            throw new ServiceException(ResultCode.FAILED, ResultMessage.FAILED);
        }
        return checkResult;
    }

    @Override
    public String getAccessToken() {
        // redis缓存 TODO
        synchronized (WeChatServiceImpl.class) {
            if (cacheTime <= DateUtil.getCurrentTime().getTime() || accessTokenDto == null || accessTokenDto.getAccessToken() == null) {
                accessTokenDto = requestAccessToken();
                cacheTime = DateUtil.getCurrentTime().getTime() + accessTokenDto.getExpiresIn() - 60;
            }
            return accessTokenDto.getAccessToken();
        }
    }

    private WeChatAccessTokenDto requestAccessToken() {
        String url = String.format(tokenUrl, appId, appSecret);
        WeChatAccessTokenDto accessTokenDto = HttpUtil.httpsRequest(url, RequestMethod.GET.name(), null, WeChatAccessTokenDto.class);
        if (accessTokenDto == null || StringUtils.isBlank(accessTokenDto.getAccessToken())) {
            throw new ServiceException(ResultCode.FAILED, ResultMessage.FAILED);
        }
        return accessTokenDto;
    }
}
