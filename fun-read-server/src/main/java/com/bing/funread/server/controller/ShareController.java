package com.bing.funread.server.controller;

import com.bing.funread.request.ShareRequest;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.ShareService;
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
 * Description:分享接口
 * Author: zhangfusheng
 * Date: 2018/4/3 下午7:54
 */
@RestController
@Api(value = "/api/v1/share", description = "分享接口")
@RequestMapping("/api/v1/share")
@Validated
public class ShareController {

    @Autowired
    private ShareService shareService;

    @RequestMapping(value = "/getUniqueId", method = RequestMethod.POST)
    @ApiOperation(value = "获取分享页面唯一ID", httpMethod = "POST", notes = "获取分享页面唯一ID")
    public Result<String> getUniqueId(@ApiParam(required = true, name = "request", value = "分享页面信息")
                                         @Valid @RequestBody ShareRequest request) {
        String uniqueId = shareService.getUniqueId(request);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, uniqueId);
    }
}
