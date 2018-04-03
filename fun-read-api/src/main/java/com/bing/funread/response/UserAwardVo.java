package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description:奖状信息
 * Author: zhangfusheng
 * Date: 2018/4/3 上午10:57
 */
@ApiModel("奖状信息")
public class UserAwardVo implements Serializable {

    @ApiModelProperty(value = "奖状内容")
    private String award;

    @ApiModelProperty(value = "奖状类型 1:课程 2:活动")
    private String type;

    @ApiModelProperty(value = "顺序")
    private Integer order;

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "UserAwardVo{" +
                "award='" + award + '\'' +
                ", type='" + type + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
