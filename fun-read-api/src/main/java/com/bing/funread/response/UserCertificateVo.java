package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description:奖状信息
 * Author: zhangfusheng
 * Date: 2018/4/3 上午10:57
 */
@ApiModel("奖状信息")
public class UserCertificateVo implements Serializable {

    @ApiModelProperty(value = "奖状内容")
    private String certificate;

    @ApiModelProperty(value = "奖状类型 1:课程 2:活动")
    private String type;

    @ApiModelProperty(value = "顺序")
    private Integer order;

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "UserCertificateVo{" +
                "certificate='" + certificate + '\'' +
                ", type='" + type + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
