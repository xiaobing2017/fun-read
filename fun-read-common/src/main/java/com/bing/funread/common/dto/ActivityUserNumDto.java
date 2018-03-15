package com.bing.funread.common.dto;

import java.io.Serializable;

/**
 * Description:活动参与人数信息
 * Author: zhangfusheng
 * Date: 2018/3/15 下午5:57
 */
public class ActivityUserNumDto implements Serializable {

    private Long activityId;

    private Integer userNum;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }
}
