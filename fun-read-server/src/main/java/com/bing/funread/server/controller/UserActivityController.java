package com.bing.funread.server.controller;

import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.response.UserActivityInfoVo;
import com.bing.funread.server.service.UserActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:用户活动接口
 * Author: zhangfusheng
 * Date: 2018/3/8 下午3:38
 */
@RestController
@Api(value = "/api/v1/activity", description = "用户活动接口")
@RequestMapping("/api/v1/activity")
@Validated
public class UserActivityController {

    @Autowired
    private UserActivityService userActivityService;

    @RequestMapping(value = "/getUserActivity/{activityId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户活动信息", httpMethod = "GET", notes = "查询用户活动信息")
    public Result<UserActivityInfoVo> getUserActivity(@ApiParam(required = true, name = "activityId", value = "活动ID")
                                                      @NotBlank(message="活动ID不能为空") @PathVariable Long activityId) {
        Long userId = null;//TODO
        UserActivityInfoVo result = userActivityService.getUserActivityInfo(userId, activityId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }
}
