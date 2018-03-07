package com.bing.funread.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Description:业务层处理异常
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:27
 */
@ResponseStatus(value = HttpStatus.OK)
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    public ServiceException(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ServiceException(String errorCode, String errorMessage, Throwable paramThrowable) {
        super(paramThrowable);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ServiceException() {
        super();
    }

    public ServiceException(String paramString, Throwable paramThrowable, boolean paramBoolean1,
                            boolean paramBoolean2) {
        super(paramString, paramThrowable, paramBoolean1, paramBoolean2);
    }

    public ServiceException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
    }

    public ServiceException(String paramString) {
        super(paramString);
    }

    public ServiceException(Throwable paramThrowable) {
        super(paramThrowable);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}