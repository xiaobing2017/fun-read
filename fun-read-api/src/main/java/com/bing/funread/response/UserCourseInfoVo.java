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
    private Integer poetryNum;

    @ApiModelProperty(value = "已加入人数")
    private Integer userNum;

    @ApiModelProperty(value = "课程名称")
    private String name;

    @ApiModelProperty(value = "分类说明")
    private String classify;

    @ApiModelProperty(value = "图片地址")
    private String picUrl;

    @ApiModelProperty(value = "已完成课时数量")
    private Integer finishedNum;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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
        if (userNum == null) {
            userNum = 0;
        }
        this.userNum = userNum;
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

    public Integer getFinishedNum() {
        return finishedNum;
    }

    public void setFinishedNum(Integer finishedNum) {
        this.finishedNum = finishedNum;
    }

    @Override
    public String toString() {
        return "UserCourseInfoVo{" +
                "courseId=" + courseId +
                "poetryNum=" + poetryNum +
                ", userNum=" + userNum +
                ", name='" + name + '\'' +
                ", classify='" + classify + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", finishedNum='" + finishedNum + '\'' +
                '}';
    }
}
