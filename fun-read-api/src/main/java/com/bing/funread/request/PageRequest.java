package com.bing.funread.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Description:分页查询参数对象
 * Author: zhangfusheng
 * Date: 2018/3/31 下午7:42
 */
@ApiModel("分页查询参数")
public class PageRequest implements Serializable {

    @ApiModelProperty("页码，最小为1")
    @Min(value = 1, message = "页码最小为1")
    private Integer pageIndex = 1;
    @ApiModelProperty("每页显示数量，最小为1")
    @Min(value = 1, message = "每页显示数量最小为1")
    private Integer pageSize = 10;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
