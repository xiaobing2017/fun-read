package com.bing.funread.common.dto;

import java.io.Serializable;

/**
 * Description:课程参与人数信息
 * Author: zhangfusheng
 * Date: 2018/3/15 下午2:05
 */
public class CourseUserNumDto implements Serializable {

    private Long courseId;

    private Integer userNum;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }
}
