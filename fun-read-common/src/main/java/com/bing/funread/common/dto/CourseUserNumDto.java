package com.bing.funread.common.dto;

import java.io.Serializable;

/**
 * Description:课程参与人数信息
 * Author: zhangfusheng
 * Date: 2018/3/15 下午2:05
 */
public class CourseUserNumDto implements Serializable {

    private Long courserId;

    private Integer userNum;

    public Long getCourserId() {
        return courserId;
    }

    public void setCourserId(Long courserId) {
        this.courserId = courserId;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }
}
