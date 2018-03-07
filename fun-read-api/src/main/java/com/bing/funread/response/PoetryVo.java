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

    @ApiModelProperty(value = "诗名", required = true)
    private String name;

    @ApiModelProperty(value = "诗词介绍", required = true)
    private String describe;


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
}
