package com.bing.funread.server.controller;

import com.bing.funread.common.domain.UserActivityAudio;
import com.bing.funread.request.PageRequest;
import com.bing.funread.response.ActivityInfoVo;
import com.bing.funread.response.ReadInfoVo;
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
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description:用户活动接口
 * Author: zhangfusheng
 * Date: 2018/3/8 下午3:38
 */
@RestController
@Api(value = "/api/v1/activity", description = "用户活动接口")
@RequestMapping("/api/v1/activity")
@Validated
public class UserActivityController extends BaseController {

    @Autowired
    private UserActivityService userActivityService;

    @RequestMapping(value = "/getUserActivity", method = RequestMethod.GET)
    @ApiOperation(value = "查询活动信息", httpMethod = "GET", notes = "查询活动信息")
    public Result<List<ActivityInfoVo>> getActivity() {
        Long userId = getUserId();
        List<ActivityInfoVo> result = userActivityService.getActivityInfo(userId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/getUserActivityStatus/{activityId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户是否已参加活动", httpMethod = "GET", notes = "查询用户是否已参加活动")
    public Result<Boolean> getUserActivityStatus(@ApiParam(required = true, name = "activityId", value = "活动ID")
                                                 @NotBlank(message="活动ID不能为空") @PathVariable Long activityId) {
        Long userId = getUserId();
        Boolean result = userActivityService.getUserActivityStatus(userId, activityId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/join/{activityId}", method = RequestMethod.GET)
    @ApiOperation(value = "用户参加活动", httpMethod = "GET", notes = "用户参加活动")
    public Result<String> join(@ApiParam(required = true, name = "activityId", value = "活动ID")
                                @NotBlank(message="活动ID不能为空") @PathVariable Long activityId) {
        Long userId = getUserId();
        userActivityService.join(userId, activityId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS);
    }

    @RequestMapping(value = "/getUserActivity/{activityId}", method = RequestMethod.POST)
    @ApiOperation(value = "查询用户活动信息", httpMethod = "POST", notes = "查询用户活动信息")
    public Result<UserActivityInfoVo> getUserActivity(@ApiParam(required = true, name = "activityId", value = "活动ID")
                                                      @NotBlank(message="活动ID不能为空") @PathVariable Long activityId,
                                                      @ApiParam(value = "分页信息", name = "pageRequest")
                                                      @Valid @RequestBody PageRequest pageRequest) {
        Long userId = getUserId();
        UserActivityInfoVo result = userActivityService.getUserActivityInfo(userId, activityId, pageRequest);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/getReadInfo/{activityId}/{poetryId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户活动诗词跟读信息", httpMethod = "GET", notes = "查询用户活动诗词跟读信息")
    public Result<List<ReadInfoVo>> getReadInfo(@ApiParam(required = true, name = "activityId", value = "活动ID")
                                                @NotBlank(message="活动ID不能为空") @PathVariable Long activityId,
                                                @ApiParam(required = true, name = "poetryId", value = "诗词ID")
                                                @NotBlank(message="诗词ID不能为空") @PathVariable Long poetryId) {
        Long userId = getUserId();
        List<ReadInfoVo> result = userActivityService.getReadInfo(userId, activityId, poetryId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/upload/audio/{activityId}/{poetryId}/{poetryInfoId}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "上传跟读文件", httpMethod = "POST", notes = "上传用户活动诗词跟读音频文件并保存")
    public Result<String> upload(@ApiParam(required = true, name = "file", value = "跟读文件")
                                 @NotNull(message="文件不能为空") @RequestParam("file") MultipartFile file,
                                 @ApiParam(required = true, name = "activityId", value = "活动ID")
                                 @NotBlank(message="活动ID不能为空") @PathVariable Long activityId,
                                 @ApiParam(required = true, name = "poetryId", value = "诗词ID")
                                 @NotBlank(message="诗词ID不能为空") @PathVariable Long poetryId,
                                 @ApiParam(required = true, name = "poetryInfoId", value = "诗句ID")
                                 @NotBlank(message="诗句ID不能为空") @PathVariable Long poetryInfoId) {
        Long userId = getUserId();
        UserActivityAudio audio = userActivityService.upload(userId, activityId, poetryId, poetryInfoId, file);
        userActivityService.saveAudio(audio);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS);
    }
}
