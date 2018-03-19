package com.bing.funread.server.controller;

import com.bing.funread.common.domain.User;
import com.bing.funread.common.utils.TokenUtil;
import com.bing.funread.request.WeChatLoginRequest;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "微信授权登录", httpMethod = "POST", notes = "微信授权登录")
    public Result<String> weChatRegister(@ApiParam(required = true, name = "request", value = "微信授权信息")
                                             @Valid @RequestBody WeChatLoginRequest login) {
        logger.info("登录参数：{}", login);
        User user = userService.login(login);
        String token = TokenUtil.createToken(user);
        logger.info("user:{}, token:{}", user.getId(), token);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, token);
    }
}
