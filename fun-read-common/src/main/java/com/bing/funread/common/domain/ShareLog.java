package com.bing.funread.common.domain;

import java.util.Date;

public class ShareLog {
    private Long id;

    private String uniqueId;

    private String userCode;

    private Integer studyDays;

    private Integer studyPoetrys;

    private String avatarUrl;

    private String nickName;

    private Long poetryId;

    private Long caId;

    private String isSkip;

    private String fromWhere;

    private String poetryName;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId == null ? null : uniqueId.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Integer getStudyDays() {
        return studyDays;
    }

    public void setStudyDays(Integer studyDays) {
        this.studyDays = studyDays;
    }

    public Integer getStudyPoetrys() {
        return studyPoetrys;
    }

    public void setStudyPoetrys(Integer studyPoetrys) {
        this.studyPoetrys = studyPoetrys;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Long getPoetryId() {
        return poetryId;
    }

    public void setPoetryId(Long poetryId) {
        this.poetryId = poetryId;
    }

    public Long getCaId() {
        return caId;
    }

    public void setCaId(Long caId) {
        this.caId = caId;
    }

    public String getIsSkip() {
        return isSkip;
    }

    public void setIsSkip(String isSkip) {
        this.isSkip = isSkip == null ? null : isSkip.trim();
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere == null ? null : fromWhere.trim();
    }

    public String getPoetryName() {
        return poetryName;
    }

    public void setPoetryName(String poetryName) {
        this.poetryName = poetryName == null ? null : poetryName.trim();
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