package com.bing.funread.server.controller;

import com.bing.funread.request.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Description:用户接口
 * Author: zhangfusheng
 * Date: 2018/3/1 上午10:21
 */
@RestController
@Api(value = "/api/user", description = "用户接口")
@RequestMapping("/api/user")
@Validated
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "普通登录", httpMethod = "POST", notes = "根据用户填写资料验证登录")
    public String login(@ApiParam(required = true, name = "loginUser", value = "用户登录信息")
                            @Valid @RequestBody LoginUser loginUser) {
        logger.info("欢迎 {} 登录", loginUser);
        return "login";
    }
}
