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
public class UserActivityInfoVo extends BaseVo {

    @ApiModelProperty(value = "活动ID")
    private Long activityId;

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "活动介绍")
    private String describe;

    @ApiModelProperty(value = "课时数量")
    private Integer poetryNum;

    @ApiModelProperty(value = "已完成任务数量")
    private Integer finishedNum;

    @ApiModelProperty(value = "活动诗词信息")
    private List<PoetryVo> poetryList;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

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

    public Integer getPoetryNum() {
        return poetryNum;
    }

    public void setPoetryNum(Integer poetryNum) {
        this.poetryNum = poetryNum;
    }

    public Integer getFinishedNum() {
        return finishedNum;
    }

    public void setFinishedNum(Integer finishedNum) {
        this.finishedNum = finishedNum;
    }

    public List<PoetryVo> getPoetryList() {
        return poetryList;
    }

    public void setPoetryList(List<PoetryVo> poetryList) {
        this.poetryList = poetryList;
    }

    @Override
    public String toString() {
        return "UserActivityInfoVo{" +
                "activityId=" + activityId +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", poetryNum=" + poetryNum +
                ", finishedNum=" + finishedNum +
                ", poetryList=" + poetryList +
                '}';
    }
}
