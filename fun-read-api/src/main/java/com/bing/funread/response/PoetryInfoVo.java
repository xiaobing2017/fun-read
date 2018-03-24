package com.bing.funread.response;

import com.bing.funread.annotation.Encryption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description:诗句信息
 * Author: zhangfusheng
 * Date: 2018/3/7 下午8:38
 */
@Encryption(value = {"picUrl", "audioUrl"})
@ApiModel(value = "诗句信息")
public class PoetryInfoVo extends BaseVo {

    @ApiModelProperty(value = "诗句ID")
    private Long id;

    @ApiModelProperty(value = "顺序")
    private Integer order;

    @ApiModelProperty(value = "诗句内容")
    private String content;

    @ApiModelProperty(value = "图片地址")
    private String picUrl;

    @ApiModelProperty(value = "音频地址")
    private String audioUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "PoetryInfoVo{" +
                "id=" + id +
                ", order=" + order +
                ", content='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                '}';
    }
}
