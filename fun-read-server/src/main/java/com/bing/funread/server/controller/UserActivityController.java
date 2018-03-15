package com.bing.funread.server.controller;

import com.bing.funread.common.domain.UserActivityAudio;
import com.bing.funread.request.ActivityPoetryRequest;
import com.bing.funread.response.ActivityInfoVo;
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

    @RequestMapping(value = "/getUserActivity/{activityId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户活动信息", httpMethod = "GET", notes = "查询用户活动信息")
    public Result<UserActivityInfoVo> getUserActivity(@ApiParam(required = true, name = "activityId", value = "活动ID")
                                                      @NotBlank(message="活动ID不能为空") @PathVariable Long activityId) {
        Long userId = getUserId();
        UserActivityInfoVo result = userActivityService.getUserActivityInfo(userId, activityId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/upload/audio", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "上传跟读文件", httpMethod = "POST", notes = "上传用户活动诗词跟读音频文件并保存")
    public Result<String> upload(@ApiParam(required = true, name = "files", value = "文件列表")
                                 @NotNull(message="文件不能为空") @RequestParam("files") MultipartFile[] files,
                                 @ApiParam(required = true, name = "request", value = "活动跟读文件上传参数")
                                 @Valid @RequestBody ActivityPoetryRequest request) {
        Long userId = getUserId();
        List<UserActivityAudio> audioList = userActivityService.upload(userId, files, request);
        userActivityService.saveUploadInfo(audioList);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS);
    }
}
