package com.bing.funread.server.controller;

import com.bing.funread.request.ShareCodeRequest;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.WeChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Description:微信接口
 * Author: zhangfusheng
 * Date: 2018/4/3 下午3:27
 */
@RestController
@Api(value = "/api/v1/weChat", description = "微信接口")
@RequestMapping("/api/v1/weChat")
@Validated
public class WeChatController {

    @Autowired
    private WeChatService weChatService;

    @RequestMapping(value = "/getAccessToken", method = RequestMethod.GET)
    @ApiOperation(value = "获取接口调用凭据", httpMethod = "GET", notes = "获取公众号的全局唯一接口调用凭据")
    public Result<String> getAccessToken() {
        String token = weChatService.getAccessToken();
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, token);
    }

    @RequestMapping(value = "/getShareCode", method = RequestMethod.POST)
    @ApiOperation(value = "获取小程序码", httpMethod = "POST", notes = "获取小程序码")
    public Result<String> getShareCode(@ApiParam(required = true, name = "request", value = "获取小程序码参数")
                                           @Valid @RequestBody ShareCodeRequest request) {
        String shareCode = weChatService.getShareCode(request);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, shareCode);
    }
}
