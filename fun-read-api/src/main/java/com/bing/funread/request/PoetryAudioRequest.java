package com.bing.funread.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description:诗句跟读信息
 * Author: zhangfusheng
 * Date: 2018/3/20 下午7:45
 */
@ApiModel("诗句跟读信息")
public class PoetryAudioRequest implements Serializable {

    @NotNull(message = "诗句ID不能为空")
    @ApiModelProperty(value = "诗句ID")
    private Long poetryInfoId;

    @NotNull(message = "文件地址不能为空")
    @ApiModelProperty(value = "文件地址")
    private String audioUrl;

    public Long getPoetryInfoId() {
        return poetryInfoId;
    }

    public void setPoetryInfoId(Long poetryInfoId) {
        this.poetryInfoId = poetryInfoId;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    @Override
    public String toString() {
        return "PoetryAudioRequest{" +
                "poetryInfoId=" + poetryInfoId +
                ", audioUrl='" + audioUrl + '\'' +
                '}';
    }
}
