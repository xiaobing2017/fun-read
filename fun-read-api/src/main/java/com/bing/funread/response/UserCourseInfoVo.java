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
public class UserCourseInfoVo implements Serializable {

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "课时数量")
    private Integer poetryCount;

    @ApiModelProperty(value = "已购买数量")
    private Integer userCount;

    @ApiModelProperty(value = "课程名称")
    private String name;

    @ApiModelProperty(value = "分类说明")
    private String classify;

    @ApiModelProperty(value = "图片地址")
    private String picUrl;

    @ApiModelProperty(value = "已解锁课程诗词数量")
    private Integer unlockNum;

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

    public Integer getUnlockNum() {
        return unlockNum;
    }

    public void setUnlockNum(Integer unlockNum) {
        this.unlockNum = unlockNum;
    }

    @Override
    public String toString() {
        return "UserCourseInfoVo{" +
                "courseId=" + courseId +
                "poetryCount=" + poetryCount +
                ", userCount=" + userCount +
                ", name='" + name + '\'' +
                ", classify='" + classify + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", unlockNum='" + unlockNum + '\'' +
                '}';
    }
}
