package com.bing.funread.server.controller;

import com.bing.funread.response.PoetryInfoVo;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.PoetryInfoService;
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
 * Description:诗句接口
 * Author: zhangfusheng
 * Date: 2018/3/7 下午8:34
 */
@RestController
@Api(value = "/api/poetryInfo", description = "诗句接口")
@RequestMapping("/api/poetryInfo")
@Validated
public class PoetryInfoController {

    @Autowired
    private PoetryInfoService poetryInfoService;

    @RequestMapping(value = "/getPoetryInfo/{poetryId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询诗句列表", httpMethod = "GET", notes = "根据诗词ID查询诗句列表")
    public Result<List<PoetryInfoVo>> getPoetryInfo(@ApiParam(required = true, name = "poetryId", value = "诗词ID")
                                                    @NotBlank(message="诗词ID不能为空") @PathVariable Long poetryId) {
        List<PoetryInfoVo> result = poetryInfoService.getPoetryInfo(poetryId);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }
}
