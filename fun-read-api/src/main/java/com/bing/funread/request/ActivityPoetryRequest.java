package com.bing.funread.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Description:活动跟读文件上传参数
 * Author: zhangfusheng
 * Date: 2018/3/11 下午4:19
 */
@ApiModel(value = "活动跟读文件上传参数")
public class ActivityPoetryRequest implements Serializable {

    @NotNull(message = "活动ID不能为空")
    @ApiModelProperty(value = "活动ID")
    private Long activityId;

    @NotNull(message = "诗词ID不能为空")
    @ApiModelProperty(value = "诗词ID")
    private Long poetryId;

    @NotNull(message = "诗句ID不能为空")
    @ApiModelProperty(value = "诗句ID")
    private List<Long> poetryInfoIdList;

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

    public List<Long> getPoetryInfoIdList() {
        return poetryInfoIdList;
    }

    public void setPoetryInfoIdList(List<Long> poetryInfoIdList) {
        this.poetryInfoIdList = poetryInfoIdList;
    }

    @Override
    public String toString() {
        return "ActivityPoetryRequest{" +
                "activityId=" + activityId +
                ", poetryId=" + poetryId +
                ", poetryInfoIdList=" + poetryInfoIdList +
                '}';
    }
}
