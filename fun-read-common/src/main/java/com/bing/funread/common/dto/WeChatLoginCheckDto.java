package com.bing.funread.common.dto;

import java.io.Serializable;

/**
 * Description:微信登录凭证校验返回结果
 * Author: zhangfusheng
 * Date: 2018/3/16 下午5:28
 */
public class WeChatLoginCheckDto implements Serializable {

    private Integer errcode;

    private String errmsg;

    private String openid;

    private String session_key;

    private String unionid;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
