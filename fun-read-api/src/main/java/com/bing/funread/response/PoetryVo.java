package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description:诗词简介
 * Author: zhangfusheng
 * Date: 2018/3/7 下午6:01
 */
@ApiModel(value = "诗词信息")
public class PoetryVo extends BaseVo {

    @ApiModelProperty(value = "诗词ID")
    private Long id;

    @ApiModelProperty(value = "诗名")
    private String name;

    @ApiModelProperty(value = "诗词介绍")
    private String describe;

    @ApiModelProperty(value = "完成状态 true:已完成 false:未完成")
    private boolean isFinish;

    @ApiModelProperty(value = "是否当前正在学习的诗词 true:是 false:否")
    private boolean isCurrent;

    @ApiModelProperty(value = "顺序")
    private Integer order;

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
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
