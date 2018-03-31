package com.bing.funread.server.controller;

import com.bing.funread.common.domain.UserCourseAudio;
import com.bing.funread.request.PageRequest;
import com.bing.funread.response.CourseDetailVo;
import com.bing.funread.response.ReadInfoVo;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.response.UserCourseInfoVo;
import com.bing.funread.response.UserStudyInfoVo;
import com.bing.funread.server.service.UserCourseService;
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
 * Description:用户课程接口
 * Author: zhangfusheng
 * Date: 2018/3/7 下午7:44
 */
@RestController
@Api(value = "/api/v1/course", description = "用户课程接口")
@RequestMapping("/api/v1/course")
@Validated
public class UserCourseController extends BaseController {

    @Autowired
    private UserCourseService userCourseService;

    @RequestMapping(value = "/getUserCourse", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户课程列表", httpMethod = "GET", notes = "查询用户课程信息列表")
    public Result<List<UserCourseInfoVo>> getUserCourse() {
        Long userId = getUserId();
        List<UserCourseInfoVo> result = userCourseService.getUserCourseInfo(userId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/getFinishedCourseName", method = RequestMethod.GET)
    @ApiOperation(value = "查询已完成课程名称列表", httpMethod = "GET", notes = "查询已完成课程名称列表")
    public Result<List<String>> getFinishedCourseName() {
        Long userId = getUserId();
        List<String> result = userCourseService.getFinishedCourseName(userId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/getUserCourse/{courseId}", method = RequestMethod.POST)
    @ApiOperation(value = "查询用户课程情况", httpMethod = "POST", notes = "查询用户课程情况")
    public Result<CourseDetailVo> getUserCourseDetail(@ApiParam(required = true, name = "courseId", value = "课程ID")
                                                      @NotBlank(message="课程ID不能为空") @PathVariable Long courseId,
                                                      @ApiParam(value = "分页信息", name = "pageRequest")
                                                      @Valid @RequestBody PageRequest pageRequest) {
        Long userId = getUserId();
        CourseDetailVo result = userCourseService.getUserCourseDetail(userId, courseId, pageRequest);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/getUserStudyInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户学习情况", httpMethod = "GET", notes = "查询学习课程情况")
    public Result<UserStudyInfoVo> getUserStudyInfo() {
        Long userId = getUserId();
        UserStudyInfoVo result = userCourseService.getUserStudyDetail(userId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/getReadInfo/{courseId}/{poetryId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户课程诗词跟读信息", httpMethod = "GET", notes = "查询用户课程诗词跟读信息")
    public Result<List<ReadInfoVo>> getReadInfo(@ApiParam(required = true, name = "courseId", value = "课程ID")
                                               @NotBlank(message="课程ID不能为空") @PathVariable Long courseId,
                                               @ApiParam(required = true, name = "poetryId", value = "诗词ID")
                                               @NotBlank(message="诗词ID不能为空") @PathVariable Long poetryId) {
        Long userId = getUserId();
        List<ReadInfoVo> result = userCourseService.getReadInfo(userId, courseId, poetryId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/upload/audio/{courseId}/{poetryId}/{poetryInfoId}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "上传跟读文件", httpMethod = "POST", notes = "上传用户课程诗词跟读音频文件并保存")
    public Result<String> upload(@ApiParam(required = true, name = "file", value = "跟读文件")
                                 @NotNull(message="文件不能为空") @RequestParam("file") MultipartFile file,
                                 @ApiParam(required = true, name = "courseId", value = "课程ID")
                                 @NotBlank(message="课程ID不能为空") @PathVariable Long courseId,
                                 @ApiParam(required = true, name = "poetryId", value = "诗词ID")
                                 @NotBlank(message="诗词ID不能为空") @PathVariable Long poetryId,
                                 @ApiParam(required = true, name = "poetryInfoId", value = "诗句ID")
                                 @NotBlank(message="诗句ID不能为空") @PathVariable Long poetryInfoId) {
        Long userId = getUserId();
        UserCourseAudio audio = userCourseService.upload(userId, courseId, poetryId, poetryInfoId, file);
        userCourseService.saveAudio(audio);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS);
    }
}
