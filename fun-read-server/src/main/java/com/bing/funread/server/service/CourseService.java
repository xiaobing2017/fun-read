package com.bing.funread.server.service;

import com.bing.funread.response.CourseDetailVo;
import com.bing.funread.response.UserCourseInfoVo;

import java.util.List;

/**
 * Description:课程接口
 * Author: zhangfusheng
 * Date: 2018/3/7 下午5:16
 */
public interface CourseService {

    /**
     * 查询首页课程信息列表
     * @return
     */
    List<UserCourseInfoVo> getCourseInfo();

    /**
     * 根据课程ID查询课程介绍
     * @param courseId
     * @return
     */
    CourseDetailVo getCourseDetail(Long courseId);
}
