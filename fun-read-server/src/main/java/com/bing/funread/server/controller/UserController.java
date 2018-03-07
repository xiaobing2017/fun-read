package com.bing.funread.server.controller;

import com.bing.funread.common.domain.User;
import com.bing.funread.common.utils.TokenUtil;
import com.bing.funread.request.LoginUser;
import com.bing.funread.request.WeChatRegRequest;
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
    @ApiOperation(value = "普通登录", httpMethod = "POST", notes = "根据用户填写资料验证登录")
    public String login(@ApiParam(required = true, name = "loginUser", value = "用户登录信息")
                            @Valid @RequestBody LoginUser loginUser) {
        logger.info("欢迎 {} 登录", loginUser);
        return "login";
    }

    @RequestMapping(value = "/weChatReg", method = RequestMethod.POST)
    @ApiOperation(value = "注册", httpMethod = "POST", notes = "根据微信信息注册")
    public Result<String> weChatRegister(@ApiParam(required = true, name = "loginUser", value = "用户微信信息")
                                             @Valid @RequestBody WeChatRegRequest request) {
        User user = userService.weChatRegister(request);
        String token = TokenUtil.createToken(user);
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, token);
    }
}
