package com.bing.funread.server.controller;

import com.bing.funread.response.ExercisesInfoVo;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.ExercisesService;
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
 * Description:练习题接口
 * Author: zhangfusheng
 * Date: 2018/3/14 上午11:29
 */
@RestController
@Api(value = "/api/exercises", description = "练习题接口")
@RequestMapping("/api/exercises")
@Validated
public class ExercisesController {

    @Autowired
    private ExercisesService exercisesService;

    @RequestMapping(value = "/getExercises/{poetryId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询诗词练习题", httpMethod = "GET", notes = "查询诗词练习题")
    public Result<List<ExercisesInfoVo>> getExercises(@ApiParam(required = true, name = "poetryId", value = "诗词ID")
                                               @NotBlank(message="诗词ID不能为空") @PathVariable Long poetryId) {
        List<ExercisesInfoVo> result = exercisesService.getExercises(poetryId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }
}
