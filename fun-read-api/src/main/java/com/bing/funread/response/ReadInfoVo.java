package com.bing.funread.response;

import com.bing.funread.annotation.Encryption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description:用户诗词跟读信息
 * Author: zhangfusheng
 * Date: 2018/3/8 下午1:41
 */
@Encryption(value = {"picUrl", "audioUrl"})
@ApiModel(value = "用户诗词跟读信息")
public class ReadInfoVo extends BaseVo {

    @ApiModelProperty(value = "诗句ID")
    private Long poetryInfoId;

    @ApiModelProperty(value = "顺序")
    private Integer order;

    @ApiModelProperty(value = "图片地址")
    private String picUrl;

    @ApiModelProperty(value = "跟读文件地址")
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    @Override
    public String toString() {
        return "ReadInfoVo{" +
                "poetryInfoId=" + poetryInfoId +
                ", order=" + order +
                ", audioUrl='" + audioUrl + '\'' +
                '}';
    }
}
