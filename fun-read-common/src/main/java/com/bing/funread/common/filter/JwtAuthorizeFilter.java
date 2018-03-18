package com.bing.funread.common.filter;

import com.bing.funread.common.utils.TokenUtil;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizeFilter implements Filter {

    @Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        final String token = httpRequest.getHeader("Authorization");
        HttpServletResponse httpResponse = (HttpServletResponse) res;
        if (StringUtils.isNotBlank(token)) {
            try {
                Claims claims = TokenUtil.parser(token);
                if (claims != null) {
                    TokenUtil.initUserToken(claims);
                    chain.doFilter(req, res);
                    return;
                }
            } catch (ExpiredJwtException e) {
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                httpResponse.getWriter().write(new ObjectMapper().writeValueAsString(
                        new Result<>(String.valueOf(ResultCode.TOKEN_INVALID), ResultMessage.TOKEN_INVALID)));
                return;
            }
        }
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE); 
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value()); 
        httpResponse.getWriter().write(new ObjectMapper().writeValueAsString(
        		new Result<>(String.valueOf(HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED.getReasonPhrase())));
        return;  
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, arg0.getServletContext());
	}
}
