package com.bing.funread.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description:用户信息
 * Author: zhangfusheng
 * Date: 2018/3/8 下午6:17
 */
@ApiModel(value = "用户信息")
public class UserInfoRequest implements Serializable {

    @ApiModelProperty(value = "用户昵称")
    private String name;

    @ApiModelProperty(value = "性别 0:未知 1:男 2:女")
    private Integer gender;

    @ApiModelProperty(value = "生日 yyyyMM")
    private String birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
