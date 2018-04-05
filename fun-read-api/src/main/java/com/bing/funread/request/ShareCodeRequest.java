package com.bing.funread.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description:获取小程序码参数
 * Author: zhangfusheng
 * Date: 2018/4/5 上午10:13
 */
@ApiModel("获取小程序码参数")
public class ShareCodeRequest extends ShareRequest {

    @ApiModelProperty(value = "必须是已经发布的小程序存在的页面（否则报错），例如 \"pages/index/index\" ,根路径前不要填加'/',不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面")
    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ShareCodeRequest{" +
                "page='" + page + '\'' +
                '}';
    }
}
