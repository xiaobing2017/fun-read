package com.bing.funread.server.service;

import com.bing.funread.response.CourseDetailVo;
import com.bing.funread.response.CourseInfoVo;
import com.bing.funread.response.UserStudyInfoVo;

import java.util.List;

/**
 * Description:用户课程接口
 * Author: zhangfusheng
 * Date: 2018/3/7 下午7:45
 */
public interface UserCourseService {

    /**
     * 查询用户课程信息列表
     * @param userId
     * @return
     */
    List<CourseInfoVo> getUserCourseInfo(Long userId);

    /**
     * 用户课程详情
     * @param userId
     * @param courseId
     * @return
     */
    CourseDetailVo getUserCourseDetail(Long userId, Long courseId);

    /**
     * 查询用户学习情况
     * @param userId
     * @return
     */
    UserStudyInfoVo getUserStudyDetail(Long userId);
}
