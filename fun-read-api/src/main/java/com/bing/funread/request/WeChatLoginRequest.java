package com.bing.funread.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description:微信小程序登录参数
 * https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html
 * https://mp.weixin.qq.com/debug/wxadoc/dev/api/open.html#wxgetuserinfoobject
 * Author: zhangfusheng
 * Date: 2018/3/16 下午4:55
 */
@ApiModel("微信小程序登录参数")
public class WeChatLoginRequest implements Serializable {

    @NotNull(message = "code不能为空")
    @ApiModelProperty(value = "用户登录凭证（有效期五分钟）")
    private String code;

    @NotNull(message = "encryptedData不能为空")
    @ApiModelProperty(value = "包括敏感数据在内的完整用户信息的加密数据")
    private String encryptedData;

    @NotNull(message = "iv不能为空")
    @ApiModelProperty(value = "加密算法的初始向量")
    private String iv;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    @Override
    public String toString() {
        return "WeChatLoginRequest{" +
                "code='" + code + '\'' +
                ", encryptedData='" + encryptedData + '\'' +
                ", iv='" + iv + '\'' +
                '}';
    }
}
