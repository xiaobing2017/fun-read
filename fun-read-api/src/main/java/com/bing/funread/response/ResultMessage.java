package com.bing.funread.response;

/**
 * 返回提示信息
 */
public class ResultMessage {
	
	/**
	 * 操作成功
	 */
    public static final String SUCCESS = "操作成功";
    
    /**
     * 操作失败
     */
    public static final String FAILED = "操作失败";

    /**
     * 验证参数异常
     */
    public static final String REQUEST_PARAMETER_ERROR = "验证请求参数异常:";

    /**
     * 账号有误
     */
    public static final String USER_ID_ERROR = "账号有误";

    private ResultMessage() {
    	
    }

}
