package com.bing.funread.common.dto;

import java.io.Serializable;

/**
 * Description:课程信息
 * Author: zhangfusheng
 * Date: 2018/3/7 下午5:39
 */
public class CourseInfoDto implements Serializable {

    private Long courseId;

    private Integer poetryCount;

    private Integer userCount;

    private String name;

    private String classify;

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
}
