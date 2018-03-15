package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

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

    @ApiModelProperty(value = "当前已解锁任务诗词ID")
    private List<Long> poetryIdList;

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

    public List<Long> getPoetryIdList() {
        return poetryIdList;
    }

    public void setPoetryIdList(List<Long> poetryIdList) {
        this.poetryIdList = poetryIdList;
    }

    @Override
    public String toString() {
        return "UserActivityInfoVo{" +
                "finishNum='" + finishNum + '\'' +
                ", unFinishNum='" + unFinishNum + '\'' +
                ", poetryIdList=" + poetryIdList +
                '}';
    }
}
