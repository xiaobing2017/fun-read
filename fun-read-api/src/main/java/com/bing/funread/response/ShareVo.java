package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description:分享页参数
 * Author: zhangfusheng
 * Date: 2018/4/3 下午7:55
 */
@ApiModel("分享页参数")
public class ShareVo implements Serializable {

    @ApiModelProperty(value = "分享码")
    private String shareCode;

    @ApiModelProperty(value = "学习天数")
    private Integer studyDays;

    @ApiModelProperty(value = "学习古诗数量")
    private Integer studyPoetrys;

    @ApiModelProperty(value = "用户头像地址")
    private String avatarUrl;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "诗词ID")
    private Long poetryId;

    @ApiModelProperty(value = "课程/活动ID")
    private Long caId;

    @ApiModelProperty(value = "是否跳过 0:非跳转;1:小程序分享后打开")
    private String isSkip;

    @ApiModelProperty(value = "类别 0:课程;1:活动")
    private String fromWhere;

    @ApiModelProperty(value = "诗名")
    private String poetryName;

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
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
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
        this.isSkip = isSkip;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public String getPoetryName() {
        return poetryName;
    }

    public void setPoetryName(String poetryName) {
        this.poetryName = poetryName;
    }

    @Override
    public String toString() {
        return "ShareRequest{" +
                "shareCode='" + shareCode + '\'' +
                ", studyDays=" + studyDays +
                ", studyPoetrys=" + studyPoetrys +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", poetryId=" + poetryId +
                ", caId=" + caId +
                ", isSkip='" + isSkip + '\'' +
                ", fromWhere='" + fromWhere + '\'' +
                ", poetryName='" + poetryName + '\'' +
                '}';
    }
}