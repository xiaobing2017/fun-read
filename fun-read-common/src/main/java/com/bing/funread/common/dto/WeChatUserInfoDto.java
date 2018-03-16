package com.bing.funread.common.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * Description:微信用户信息
 * https://mp.weixin.qq.com/debug/wxadoc/dev/api/signature.html
 * Author: zhangfusheng
 * Date: 2018/3/16 下午8:07
 */
public class WeChatUserInfoDto implements Serializable {

    private String openId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private int gender;

    private String city;

    private String province;

    private String country;

    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），
     * 用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    private String avatarUrl;

    private String unionId;

    private Map<String, Object> watermark;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Map<String, Object> getWatermark() {
        return watermark;
    }

    public void setWatermark(Map<String, Object> watermark) {
        this.watermark = watermark;
    }
}
