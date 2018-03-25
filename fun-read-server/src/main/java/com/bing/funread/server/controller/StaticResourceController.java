package com.bing.funread.server.controller;

import com.bing.funread.common.utils.EncryptionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Description:静态资源文件重定向
 * Author: zhangfusheng
 * Date: 2018/3/24 下午2:35
 */
@Controller
@Api(value = "/static", description = "练习题接口")
@RequestMapping("/static")
public class StaticResourceController {

    private static final String FORWARD = "forward:/";

    @RequestMapping(value = "/{filepath}", method = RequestMethod.GET)
    @ApiOperation(value = "访问静态资源文件", httpMethod = "GET", notes = "访问静态资源文件")
    public String accessFile(@ApiParam(required = true, name = "filepath", value = "文件路径")
                               @NotBlank(message="文件路径不能为空") @PathVariable String filepath,
                               RedirectAttributes attr) {
        String realPath = EncryptionUtil.decrypt(filepath);
        if (StringUtils.isEmpty(realPath)) {
            return null;
        }
        return FORWARD + realPath;
    }
}
