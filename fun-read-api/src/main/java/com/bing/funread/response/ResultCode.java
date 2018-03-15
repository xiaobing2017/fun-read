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
	public static final String REQUEST_PARAMETER_ERROR = "11111";

	/**
	 * 账号有误
	 */
	public static final String USER_ID_ERROR = "10001";

	/**
	 * 上传文件为空
	 */
	public static final String UPLOAD_FILE_EMPTY = "10002";

	/**
	 * 上传文件数量与诗句数量不一致
	 */
	public static final String UPLOAD_FILE_POETRY_NUM_DIFF = "10003";

	/**
	 * 用户课程不存在
	 */
	public static final String USER_COURSE_ERROR = "10004";

	/**
	 * 课程诗词不存在
	 */
	public static final String COURSE_POETRY_ERROR = "10005";

	/**
	 * 诗词内容为空
	 */
	public static final String POETRY_INFO_EMPTY = "10006";

	/**
	 * 上传文件不完整
	 */
	public static final String NOT_ALL_POETRY_FILE = "10007";

	/**
	 * 诗句不存在
	 */
	public static final String POETRY_INFO_NOT_EXISTS = "10008";

	/**
	 * 用户活动不存在
	 */
	public static final String USER_ACTIVITY_ERROR = "10009";

	/**
	 * 活动诗词不存在
	 */
	public static final String ACTIVITY_POETRY_ERROR = "10010";


	private ResultCode() {

	}
}
