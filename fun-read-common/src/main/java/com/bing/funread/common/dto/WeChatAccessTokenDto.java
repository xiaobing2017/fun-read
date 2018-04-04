package com.bing.funread.common.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Description:获取公众号的全局唯一接口调用凭据返回结果
 * Author: zhangfusheng
 * Date: 2018/4/3 下午3:45
 */
//@JsonIgnoreProperties(ignoreUnknown=true)
public class WeChatAccessTokenDto {

    /**
     * 获取到的凭证
     */
    @JSONField(name = "access_token")
    private String accessToken;

    /**
     * 凭证有效时间，单位：秒
     */
    @JSONField(name = "expires_in")
    private long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "WeChatAccessTokenDto{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
