package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description:诗词简介
 * Author: zhangfusheng
 * Date: 2018/3/7 下午6:01
 */
@ApiModel(value = "诗词信息")
public class PoetryVo implements Serializable {

    @ApiModelProperty(value = "诗名")
    private String name;

    @ApiModelProperty(value = "诗词介绍")
    private String describe;

    @ApiModelProperty(value = "完成状态 true:已完成 false:未完成")
    private boolean isFinish;

    @ApiModelProperty(value = "是否当前正在学习的诗词 true:是 false:否")
    private boolean isCurrent;

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
}
