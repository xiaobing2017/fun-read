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
	 * token无效，请先登录
	 */
	public static final String TOKEN_INVALID = "11112";

	/**
	 * token已过期
	 */
	public static final String TOKEN_EXPIRED = "11113";

	/**
	 * 账号有误
	 */
	public static final String USER_ID_ERROR = "10001";

	/**
	 * 用户课程不存在
	 */
	public static final String USER_COURSE_ERROR = "10004";

	/**
	 * 课程诗词不存在
	 */
	public static final String COURSE_POETRY_ERROR = "10005";

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

	/**
	 * 活动无效
	 */
	public static final String ACTIVITY_INVALID = "10011";

	/**
	 * 活动未开始
	 */
	public static final String ACTIVITY_NOT_START = "10012";

	/**
	 * 活动已结束
	 */
	public static final String ACTIVITY_HAS_END = "10013";

	/**
	 * 文件上传失败
	 */
	public static final String FILE_UPLOAD_FAILURE = "10014";

	private ResultCode() {

	}
}
