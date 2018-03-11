package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description:首页banner图信息
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:55
 */
@ApiModel(value = "首页banner图信息")
public class HomeBannerVo implements Serializable {

    @ApiModelProperty(value = "banner图地址")
    private String bannerUrl;

    @ApiModelProperty(value = "顺序")
    private Integer order;

    @ApiModelProperty(value = "链接地址")
    private String linkUrl;

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    @Override
    public String toString() {
        return "HomeBannerVo{" +
                "bannerUrl='" + bannerUrl + '\'' +
                ", order=" + order +
                ", linkUrl=" + linkUrl +
                '}';
    }
}
