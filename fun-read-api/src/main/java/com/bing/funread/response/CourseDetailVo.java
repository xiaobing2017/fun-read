package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Description:课程详情
 * Author: zhangfusheng
 * Date: 2018/3/7 下午6:00
 */
@ApiModel(value = "课程详情")
public class CourseDetailVo extends BaseVo {

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "课程名称")
    private String name;

    @ApiModelProperty(value = "课程介绍")
    private String describe;

    @ApiModelProperty(value = "课时数量")
    private Integer poetryNum;

    @ApiModelProperty(value = "已完成课时数量")
    private Integer finishedNum;

    @ApiModelProperty(value = "课程诗词信息")
    private List<PoetryVo> poetryList;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getPoetryNum() {
        return poetryNum;
    }

    public void setPoetryNum(Integer poetryNum) {
        this.poetryNum = poetryNum;
    }

    public Integer getFinishedNum() {
        return finishedNum;
    }

    public void setFinishedNum(Integer finishedNum) {
        this.finishedNum = finishedNum;
    }

    public List<PoetryVo> getPoetryList() {
        return poetryList;
    }

    public void setPoetryList(List<PoetryVo> poetryList) {
        this.poetryList = poetryList;
    }

    @Override
    public String toString() {
        return "CourseDetailVo{" +
                "courseId='" + courseId + '\'' +
                "name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", poetryNum='" + poetryNum + '\'' +
                ", finishedNum='" + finishedNum + '\'' +
                ", poetryList=" + poetryList +
                '}';
    }
}
