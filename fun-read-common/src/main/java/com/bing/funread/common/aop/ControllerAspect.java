package com.bing.funread.common.aop;

import com.bing.funread.common.exception.ServiceException;
import com.bing.funread.common.utils.TokenUtil;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:接口访问切面
 * Author: zhangfusheng
 * Date: 2018/3/9 上午11:47
 */
@Aspect
@Component
public class ControllerAspect {

    @Before(value = "execution(* com.bing.funread.server.controller.*.*(..))")
    public void authToken(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        final String token = request.getHeader("Authorization");

        if (StringUtils.isNotBlank(token)) {
            try {
                Claims claims = TokenUtil.parser(token);
                if (claims != null) {
                    TokenUtil.initUserToken(claims);


                }
            } catch (ExpiredJwtException e) {
//                throw new ServiceException(ResultCode.TOKEN_EXPIREDU_ERRORR_CODE, ResultMessage.TOKEN_EXPIREDU_ERRORR_MSG);
            }
        }
    }
}
