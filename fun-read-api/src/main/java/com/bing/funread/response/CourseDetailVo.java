package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Description:课程详情
 * Author: zhangfusheng
 * Date: 2018/3/7 下午6:00
 */
@ApiModel(value = "课程详情")
public class CourseDetailVo implements Serializable {

    @ApiModelProperty(value = "课程名称", required = true)
    private String name;

    @ApiModelProperty(value = "课程介绍", required = true)
    private String describe;

    @ApiModelProperty(value = "课程诗词信息", required = true)
    private List<PoetryVo> poetryList;

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

    public List<PoetryVo> getPoetryList() {
        return poetryList;
    }

    public void setPoetryList(List<PoetryVo> poetryList) {
        this.poetryList = poetryList;
    }

    @Override
    public String toString() {
        return "CourseDetailVo{" +
                "name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", poetryList=" + poetryList +
                '}';
    }
}
