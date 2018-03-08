package com.bing.funread.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description:用户学习情况
 * Author: zhangfusheng
 * Date: 2018/3/7 下午8:51
 */
@ApiModel(value = "用户学习情况")
public class UserStudyInfoVo {

    @ApiModelProperty(value = "学习天数")
    private Integer studyDays;

    @ApiModelProperty(value = "学习多少首古诗")
    private Integer studyPoetrys;

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
}
