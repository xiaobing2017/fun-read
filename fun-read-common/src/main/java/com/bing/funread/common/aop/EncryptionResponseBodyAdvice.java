//package com.bing.funread.common.aop;
//
//import com.bing.funread.common.utils.EncryptionAnnotationUtil;
//import com.bing.funread.response.Result;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
///**
// * url加密处理
// */
//@ControllerAdvice(basePackages = "com.bing.funread.server.controller")
//public class EncryptionResponseBodyAdvice implements ResponseBodyAdvice<Result> {
//
//	private static final Logger logger = LoggerFactory.getLogger(EncryptionResponseBodyAdvice.class);
//
//	@Override
//	public boolean supports(MethodParameter returnType, Class converterType) {
//		return true;
//	}
//
//	@Override
//	public Result beforeBodyWrite(Result body, MethodParameter returnType, MediaType selectedContentType,
//			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//		try {
//			EncryptionAnnotationUtil.encrypt(body.getValue());
//			return body;
//		} catch (Exception e) {
//			logger.error("url加密处理失败:{}", e);
//			return body;
//		}
//	}
//}
