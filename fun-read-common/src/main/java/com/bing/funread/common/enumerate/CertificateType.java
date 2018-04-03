package com.bing.funread.common.enumerate;

/**
 * Description:奖状类型
 * Author: zhangfusheng
 * Date: 2018/4/3 上午11:09
 */
public enum CertificateType {

    COURSE("1", "课程"),
    ACTIVITY("2", "活动");

    private String type;

    private String name;

    CertificateType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
