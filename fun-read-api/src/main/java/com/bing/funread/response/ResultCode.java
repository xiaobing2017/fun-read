package com.bing.funread.response;

/**
 * 返回状态码
 */
public class ResultCode {
	
	/**
	 * 操作成功
	 */
	public static final String SUCCESS = "00000";
	
    /**
     * 操作失败
     */
	public static final String FAILED = "99999";

	/**
	 * 验证参数异常
	 */
	public static final String REQUEST_PARAMETER_ERROR_CODE = "11111";

	private ResultCode() {

	}
}
