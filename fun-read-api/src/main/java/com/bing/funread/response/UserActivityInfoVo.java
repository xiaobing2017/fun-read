package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description:用户活动信息
 * Author: zhangfusheng
 * Date: 2018/3/8 下午3:45
 */
@ApiModel(value = "用户活动信息")
public class UserActivityInfoVo {

    @ApiModelProperty(value = "已完成任务数量")
    private Integer finishNum;

    @ApiModelProperty(value = "未完成任务数量")
    private Integer unFinishNum;

    @ApiModelProperty(value = "当前任务诗词ID")
    private Long poetryId;

    public Integer getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Integer finishNum) {
        this.finishNum = finishNum;
    }

    public Integer getUnFinishNum() {
        return unFinishNum;
    }

    public void setUnFinishNum(Integer unFinishNum) {
        this.unFinishNum = unFinishNum;
    }

    public Long getPoetryId() {
        return poetryId;
    }

    public void setPoetryId(Long poetryId) {
        this.poetryId = poetryId;
    }

    @Override
    public String toString() {
        return "UserActivityInfoVo{" +
                "finishNum='" + finishNum + '\'' +
                ", unFinishNum='" + unFinishNum + '\'' +
                ", poetryId=" + poetryId +
                '}';
    }
}
