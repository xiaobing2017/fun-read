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

    @JSONField(name = "errcode")
    private String errCode;

    @JSONField(name = "errmsg")
    private String errMsg;

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

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "WeChatAccessTokenDto{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", errCode=" + errCode +
                ", errMsg=" + errMsg +
                '}';
    }
}
