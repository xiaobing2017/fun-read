package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:活动简介
 * Author: zhangfusheng
 * Date: 2018/3/8 下午2:50
 */
@ApiModel(value = "活动简介")
public class ActivityVo implements Serializable {

    @ApiModelProperty(value = "活动ID")
    private Long id;

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "活动详情")
    private String describe;

    @ApiModelProperty(value = "图片地址")
    private String bannerUrl;

    @ApiModelProperty(value = "活动开始时间")
    private Date startDate;

    @ApiModelProperty(value = "活动结束时间")
    private Date endDate;

    @ApiModelProperty(value = "诗词数量")
    private Integer poetryNum;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPoetryNum() {
        return poetryNum;
    }

    public void setPoetryNum(Integer poetryNum) {
        this.poetryNum = poetryNum;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "ActivityVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", poetryNum=" + poetryNum +
                ", isValid='" + isValid + '\'' +
                '}';
    }
}
