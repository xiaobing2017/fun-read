package com.bing.funread.common.dto;

import java.io.Serializable;

/**
 * Description:用户课程诗词跟读信息
 * Author: zhangfusheng
 * Date: 2018/3/8 下午1:49
 */
public class ReadInfoDto implements Serializable {

    private Long poetryInfoId;

    private Integer order;

    private String audioUrl;

    public Long getPoetryInfoId() {
        return poetryInfoId;
    }

    public void setPoetryInfoId(Long poetryInfoId) {
        this.poetryInfoId = poetryInfoId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
