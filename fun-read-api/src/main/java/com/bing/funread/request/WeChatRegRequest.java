package com.bing.funread.request;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Description:微信用户注册
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:16
 */
@ApiModel(value = "微信信息注册请求对象")
public class WeChatRegRequest implements Serializable {

    private String unionid;

    private String openid;

    private String nickname;

    private String sex;

    private String county;

    private String province;

    private String city;

    private String headimgurl;

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    @Override
    public String toString() {
        return "WeChatRegRequest{" +
                "unionid='" + unionid + '\'' +
                ", openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", county='" + county + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                '}';
    }
}
