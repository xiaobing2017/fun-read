package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description:活动信息
 * Author: zhangfusheng
 * Date: 2018/3/8 下午3:45
 */
@ApiModel(value = "活动信息")
public class ActivityInfoVo {

    @ApiModelProperty(value = "活动ID")
    private Integer id;

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "图片地址")
    private String bannerUrl;

    @ApiModelProperty(value = "课时数量")
    private Integer poetryNum;

    @ApiModelProperty(value = "已加入人数")
    private Integer userNum;

    @ApiModelProperty(value = "是否已参加 true是 false否")
    private boolean isJoin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Integer getPoetryNum() {
        return poetryNum;
    }

    public void setPoetryNum(Integer poetryNum) {
        this.poetryNum = poetryNum;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean join) {
        isJoin = join;
    }

    @Override
    public String toString() {
        return "ActivityInfoVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", poetryNum=" + poetryNum +
                ", userNum=" + userNum +
                ", isJoin=" + isJoin +
                '}';
    }
}
