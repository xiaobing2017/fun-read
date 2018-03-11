package com.bing.funread.server.controller;

import com.bing.funread.response.CourseDetailVo;
import com.bing.funread.response.UserCourseInfoVo;
import com.bing.funread.response.ReadInfoVo;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.response.UserStudyInfoVo;
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

    @RequestMapping(value = "/getUserCourse/{courseId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户课程情况", httpMethod = "GET", notes = "查询用户课程情况")
    public Result<CourseDetailVo> getUserCourseDetail(@ApiParam(required = true, name = "courseId", value = "课程ID")
                                                      @NotBlank(message="课程ID不能为空") @PathVariable Long courseId) {
        Long userId = getUserId();
        CourseDetailVo result = userCourseService.getUserCourseDetail(userId, courseId);
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
    public Result<ReadInfoVo> getReadInfo(@ApiParam(required = true, name = "courseId", value = "课程ID")
                                               @NotBlank(message="课程ID不能为空") @PathVariable Long courseId,
                                               @ApiParam(required = true, name = "poetryId", value = "诗词ID")
                                               @NotBlank(message="诗词ID不能为空") @PathVariable Long poetryId) {
        Long userId = getUserId();
        ReadInfoVo result = userCourseService.getReadInfo(userId, courseId, poetryId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    //TODO 上次跟读音频文件接口
}
