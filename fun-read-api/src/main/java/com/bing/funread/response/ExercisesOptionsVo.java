package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description:练习题选项信息
 * Author: zhangfusheng
 * Date: 2018/3/14 下午12:01
 */
@ApiModel("练习题选项信息")
public class ExercisesOptionsVo implements Serializable {

    @ApiModelProperty(value = "选项ID")
    private Long id;

    @ApiModelProperty(value = "图片地址")
    private String picUrl;

    @ApiModelProperty(value = "排序")
    private Integer order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "ExercisesOptionsVo{" +
                "id=" + id +
                ", picUrl='" + picUrl + '\'' +
                ", order=" + order +
                '}';
    }
}
