package com.bing.funread.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.bing.funread.common.dto.TempAutoDto;
import com.bing.funread.common.utils.DateUtil;
import com.bing.funread.common.utils.EncryptionUtil;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:权限接口
 * Author: zhangfusheng
 * Date: Date: 2018/4/2 下午2:25
 */
@RestController
@Api(value = "/api/v1/auth", description = "权限接口")
@RequestMapping("/api/v1/auth")
@Validated
public class AuthController extends BaseController {

    @RequestMapping(value = "/getShareCode", method = RequestMethod.GET)
    @ApiOperation(value = "获取分享码", httpMethod = "GET", notes = "获取分享码")
    public Result<String> getShareCode() {
        Long userId = getUserId();
        TempAutoDto dto = new TempAutoDto(userId, DateUtil.getCurrentTime());
        String shareCode = EncryptionUtil.encrypt(JSONObject.toJSONString(dto));
        return new Result<>(ResultCode.SUCCESS, ResultMessage.SUCCESS, shareCode);
    }
}
