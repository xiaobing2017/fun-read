package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description:课程信息
 * Author: zhangfusheng
 * Date: 2018/3/7 下午5:22
 */
@ApiModel(value = "课程信息")
public class CourseInfoVo implements Serializable {

    @ApiModelProperty(value = "课程ID", required = true)
    private Long courseId;

    @ApiModelProperty(value = "课时数量", required = true)
    private Integer poetryCount;

    @ApiModelProperty(value = "已购买数量", required = true)
    private Integer userCount;

    @ApiModelProperty(value = "课程名称", required = true)
    private String name;

    @ApiModelProperty(value = "分类说明", required = true)
    private String classify;

    @ApiModelProperty(value = "图片地址", required = true)
    private String picUrl;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getPoetryCount() {
        return poetryCount;
    }

    public void setPoetryCount(Integer poetryCount) {
        this.poetryCount = poetryCount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "CourseInfoVo{" +
                "courseId=" + courseId +
                "poetryCount=" + poetryCount +
                ", userCount=" + userCount +
                ", name='" + name + '\'' +
                ", classify='" + classify + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
