package com.bing.funread.server.controller;

import com.bing.funread.response.ActivityVo;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.ActivityService;
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
 * Description:活动接口
 * Author: zhangfusheng
 * Date: 2018/3/8 下午2:47
 */
@RestController
@Api(value = "/api/activity", description = "活动接口")
@RequestMapping("/api/activity")
@Validated
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/getActivity/{activityId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询活动介绍", httpMethod = "GET", notes = "查询活动介绍")
    public Result<ActivityVo> getActivity(@ApiParam(required = true, name = "activityId", value = "活动ID")
                                          @NotBlank(message="活动ID不能为空") @PathVariable Long activityId) {
        ActivityVo result = activityService.getActivityInfo(activityId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }
}
