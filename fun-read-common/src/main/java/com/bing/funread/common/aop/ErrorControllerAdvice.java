package com.bing.funread.common.aop;

import com.bing.funread.common.exception.ServiceException;
import com.bing.funread.response.Result;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice(basePackages = "com.bing.funread.server.controller")
public class ErrorControllerAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorControllerAdvice.class);
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	@ResponseBody
	public Result<String> handleValidationFailure(MethodArgumentNotValidException ex) {
		logger.error(ex.getMessage(), ex);
		BindingResult bindingResult = ex.getBindingResult();
		FieldError fieldError = bindingResult.getFieldError();
		return new Result<>(ResultCode.REQUEST_PARAMETER_ERROR,
				ResultMessage.REQUEST_PARAMETER_ERROR + fieldError.getDefaultMessage());
	}
	
	@ExceptionHandler(value = {ConstraintViolationException.class})
	@ResponseBody
	Result<String> handleValidationFailure(ConstraintViolationException ex) {
		logger.error(ex.getMessage(), ex);
		 String errorMessage = null;
 	     for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
 	    	errorMessage = violation.getMessage();
 	    	break;
 	     }
		return new Result<>(ResultCode.REQUEST_PARAMETER_ERROR,
				ResultMessage.REQUEST_PARAMETER_ERROR + errorMessage);
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	Result<String> handleControllerException(HttpServletRequest request, Throwable ex) {
		HttpStatus status = getStatus(request);
		logger.error(ex.getMessage(), ex);
		return new Result<>(String.valueOf(status.value()), status.getReasonPhrase());
	}

	private static HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	Result<String> handleServiceException(ServiceException e) {
		return new Result<>(e.getErrorCode(), e.getErrorMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	Result<String> handleMessageNotReadableException(HttpMessageNotReadableException e){
        String message;
        if(e.contains(InvalidFormatException.class)){
            message = e.getRootCause().getLocalizedMessage();
        } else {
            message = e.getLocalizedMessage();
        }
		return new Result<>(ResultCode.REQUEST_PARAMETER_ERROR, message);
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	Result<String> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
		logger.error(ex.getMessage(), ex);
		return new Result<>(ResultCode.REQUEST_PARAMETER_ERROR, "文件大于" + ex.getMaxUploadSize() + "B");
	}
}