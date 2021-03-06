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
     * token无效，请先登录
     */
    public static final String TOKEN_INVALID = "token无效，请先登录";

    /**
     * token已过期
     */
    public static final String TOKEN_EXPIRED = "token已过期";

    /**
     * 账号有误
     */
    public static final String USER_ID_ERROR = "账号有误";

    /**
     * 用户课程不存在
     */
    public static final String USER_COURSE_ERROR = "用户课程不存在";

    /**
     * 课程诗词不存在
     */
    public static final String COURSE_POETRY_ERROR = "课程诗词不存在";

    /**
     * 诗句不存在
     */
    public static final String POETRY_INFO_NOT_EXISTS = "诗句不存在";

    /**
     * 用户活动不存在
     */
    public static final String USER_ACTIVITY_ERROR = "用户活动不存在";

    /**
     * 活动诗词不存在
     */
    public static final String ACTIVITY_POETRY_ERROR = "活动诗词不存在";

    /**
     * 活动无效
     */
    public static final String ACTIVITY_INVALID = "活动无效";

    /**
     * 活动未开始
     */
    public static final String ACTIVITY_NOT_START = "活动未开始";

    /**
     * 活动已结束
     */
    public static final String ACTIVITY_HAS_END = "活动已结束";

    /**
     * 文件上传失败
     */
    public static final String FILE_UPLOAD_FAILURE = "文件上传失败";

    private ResultMessage() {
    	
    }

}
