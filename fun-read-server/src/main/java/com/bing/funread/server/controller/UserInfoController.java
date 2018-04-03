package com.bing.funread.server.controller;

import com.bing.funread.request.PageRequest;
import com.bing.funread.request.UserInfoRequest;
import com.bing.funread.response.AwardInfoVo;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.response.UserInfoVo;
import com.bing.funread.response.UserStudyInfoVo;
import com.bing.funread.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Description:用户信息接口
 * Author: zhangfusheng
 * Date: 2018/3/8 下午6:15
 */
@RestController
@Api(value = "/api/v1/user", description = "用户信息接口")
@RequestMapping("/api/v1/user")
@Validated
public class UserInfoController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户资料", httpMethod = "GET", notes = "查询用户资料")
    public Result<UserInfoVo> getUserInfo() {
        Long userId = getUserId();
        UserInfoVo result = userService.getUserInfo(userId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户资料", httpMethod = "POST", notes = "更新用户资料")
    public Result<String> updateUserInfo(@ApiParam(required = true, name = "userInfo", value = "用户资料")
                                             @Valid @RequestBody UserInfoRequest userInfo) {
        Long userId = getUserId();
        userService.updateUserInfo(userId, userInfo);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS);
    }

    @RequestMapping(value = "/bindPhone/{phone}", method = RequestMethod.GET)
    @ApiOperation(value = "绑定手机号", httpMethod = "GET", notes = "绑定手机号")
    public Result<Boolean> bindPhone(@ApiParam(required = true, name = "phone", value = "手机号")
                                    @NotBlank(message="手机号不能为空") @PathVariable String phone) {
        Long userId = getUserId();
        boolean result = userService.bindPhone(userId, phone);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/getUserStudyInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户学习情况", httpMethod = "GET", notes = "查询学习课程情况")
    public Result<UserStudyInfoVo> getUserStudyInfo() {
        Long userId = getUserId();
        UserStudyInfoVo result = userService.getUserStudyDetail(userId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/getCertificate", method = RequestMethod.POST)
    @ApiOperation(value = "查询奖状列表", httpMethod = "POST", notes = "查询奖状列表")
    public Result<AwardInfoVo> getCertificate(@ApiParam(value = "分页信息", name = "pageRequest")
                                              @Valid @RequestBody PageRequest pageRequest) {
        Long userId = getUserId();
        AwardInfoVo result = userService.getAward(userId, pageRequest);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }
}
