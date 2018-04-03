package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Description:奖状汇总信息
 * Author: zhangfusheng
 * Date: 2018/4/3 下午2:10
 */
@ApiModel("奖状汇总信息")
public class CertificateInfoVo {

    @ApiModelProperty(value = "奖状总数")
    private Integer total;

    @ApiModelProperty(value = "奖状信息列表")
    private List<UserCertificateVo> userCertificateVoList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<UserCertificateVo> getUserCertificateVoList() {
        return userCertificateVoList;
    }

    public void setUserCertificateVoList(List<UserCertificateVo> userCertificateVoList) {
        this.userCertificateVoList = userCertificateVoList;
    }

    @Override
    public String toString() {
        return "CertificateInfoVo{" +
                "total=" + total +
                ", userCertificateVoList=" + userCertificateVoList +
                '}';
    }
}
