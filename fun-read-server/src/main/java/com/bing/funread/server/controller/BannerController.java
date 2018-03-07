package com.bing.funread.server.controller;

import com.bing.funread.response.HomeBannerVo;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:首页banner图接口
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:52
 */
@RestController
@Api(value = "/api/banner", description = "banner图接口")
@RequestMapping("/api/banner")
@Validated
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/getBanner", method = RequestMethod.GET)
    @ApiOperation(value = "查询首页banner图", httpMethod = "GET", notes = "查询首页banner图")
    public Result<List<HomeBannerVo>> getBanner() {
        List<HomeBannerVo> result = bannerService.getBanner();
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, result);
    }
}
