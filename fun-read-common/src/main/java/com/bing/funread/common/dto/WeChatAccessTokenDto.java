package com.bing.funread.common.dto;

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
    private String access_token;

    /**
     * 凭证有效时间，单位：秒
     */
    private long expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "WeChatAccessTokenDto{" +
                "access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                '}';
    }
}
