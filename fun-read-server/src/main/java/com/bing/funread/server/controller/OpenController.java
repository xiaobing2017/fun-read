package com.bing.funread.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.bing.funread.common.dto.TempAutoDto;
import com.bing.funread.common.utils.EncryptionUtil;
import com.bing.funread.response.ReadInfoVo;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.UserActivityService;
import com.bing.funread.server.service.UserCourseService;
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

import java.util.List;

/**
 * Description:免认证接口
 * Author: zhangfusheng
 * Date: 2018/4/2 下午2:39
 */
@RestController
@Api(value = "/api/open", description = "免认证接口")
@RequestMapping("/api/open")
@Validated
public class OpenController {

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private UserActivityService userActivityService;

    @RequestMapping(value = "/course/getReadInfo/{courseId}/{poetryId}/{shareCode}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户课程诗词跟读信息", httpMethod = "GET", notes = "根据分享码查询用户课程诗词跟读信息")
    public Result<List<ReadInfoVo>> getCourseReadInfoByShareCode(@ApiParam(required = true, name = "courseId", value = "课程ID")
                                                           @NotBlank(message="课程ID不能为空") @PathVariable Long courseId,
                                                           @ApiParam(required = true, name = "poetryId", value = "诗词ID")
                                                           @NotBlank(message="诗词ID不能为空") @PathVariable Long poetryId,
                                                           @ApiParam(required = true, name = "shareCode", value = "分享码")
                                                           @NotBlank(message="分享码不能为空") @PathVariable String shareCode) {
        TempAutoDto dto = JSONObject.parseObject(EncryptionUtil.decrypt(shareCode), TempAutoDto.class);
        Long userId = dto.getUserId();
        List<ReadInfoVo> result = userCourseService.getReadInfo(userId, courseId, poetryId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/activity/getReadInfo/{activityId}/{poetryId}/{shareCode}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户课程诗词跟读信息", httpMethod = "GET", notes = "根据分享码查询用户课程诗词跟读信息")
    public Result<List<ReadInfoVo>> getActivityReadInfoByShareCode(@ApiParam(required = true, name = "activityId", value = "活动ID")
                                                           @NotBlank(message="活动ID不能为空") @PathVariable Long activityId,
                                                           @ApiParam(required = true, name = "poetryId", value = "诗词ID")
                                                           @NotBlank(message="诗词ID不能为空") @PathVariable Long poetryId,
                                                           @ApiParam(required = true, name = "shareCode", value = "分享码")
                                                           @NotBlank(message="分享码不能为空") @PathVariable String shareCode) {
        TempAutoDto dto = JSONObject.parseObject(EncryptionUtil.decrypt(shareCode), TempAutoDto.class);
        Long userId = dto.getUserId();
        List<ReadInfoVo> result = userActivityService.getReadInfo(userId, activityId, poetryId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }
}
