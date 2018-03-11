package com.bing.funread.server.controller;

import com.bing.funread.response.CourseDetailVo;
import com.bing.funread.response.UserCourseInfoVo;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.CourseService;
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
 * Description:课程接口
 * Author: zhangfusheng
 * Date: 2018/3/7 下午5:15
 */
@RestController
@Api(value = "/api/course", description = "课程接口")
@RequestMapping("/api/course")
@Validated
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/getCourse", method = RequestMethod.GET)
    @ApiOperation(value = "查询课程列表", httpMethod = "GET", notes = "查询首页课程信息列表")
    public Result<List<UserCourseInfoVo>> getCourse() {
        List<UserCourseInfoVo> result = courseService.getCourseInfo();
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }

    @RequestMapping(value = "/getCourseDetail/{courseId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询课程说明", httpMethod = "GET", notes = "查询课程说明")
    public Result<CourseDetailVo> getCourseDetail(@ApiParam(required = true, name = "courseId", value = "课程ID")
                                                  @NotBlank(message="课程ID不能为空") @PathVariable Long courseId) {
        CourseDetailVo result = courseService.getCourseDetail(courseId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }
}
