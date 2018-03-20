package com.bing.funread.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Description:跟读文件地址信息保存
 * Author: zhangfusheng
 * Date: 2018/3/20 下午7:32
 */
@ApiModel("跟读文件地址信息保存")
public class ActivityAudioRequest implements Serializable {

    @NotNull(message = "活动ID不能为空")
    @ApiModelProperty(value = "活动ID")
    private Long activityId;

    @NotNull(message = "诗词ID不能为空")
    @ApiModelProperty(value = "诗词ID")
    private Long poetryId;

    @Valid
    @NotNull(message = "诗句跟读信息列表不能为空")
    @ApiModelProperty(value = "诗句跟读信息列表")
    private List<PoetryAudioRequest> poetryAudioList;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getPoetryId() {
        return poetryId;
    }

    public void setPoetryId(Long poetryId) {
        this.poetryId = poetryId;
    }

    public List<PoetryAudioRequest> getPoetryAudioList() {
        return poetryAudioList;
    }

    public void setPoetryAudioList(List<PoetryAudioRequest> poetryAudioList) {
        this.poetryAudioList = poetryAudioList;
    }

    @Override
    public String toString() {
        return "ActivityAudioRequest{" +
                "activityId=" + activityId +
                ", poetryId=" + poetryId +
                ", poetryAudioList=" + poetryAudioList +
                '}';
    }
}
