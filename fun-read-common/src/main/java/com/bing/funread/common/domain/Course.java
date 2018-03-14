package com.bing.funread.common.domain;

import java.util.Date;

public class Course {
    private Long id;

    private String name;

    private String describe;

    private String classify;

    private Integer poetryNum;

    private String picUrl;

    private Integer finalTestNum;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify == null ? null : classify.trim();
    }

    public Integer getPoetryNum() {
        return poetryNum;
    }

    public void setPoetryNum(Integer poetryNum) {
        this.poetryNum = poetryNum;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Integer getFinalTestNum() {
        return finalTestNum;
    }

    public void setFinalTestNum(Integer finalTestNum) {
        this.finalTestNum = finalTestNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}