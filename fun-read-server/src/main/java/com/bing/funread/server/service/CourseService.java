package com.bing.funread.server.service;

import com.bing.funread.response.CourseDetailVo;

/**
 * Description:课程接口
 * Author: zhangfusheng
 * Date: 2018/3/7 下午5:16
 */
public interface CourseService {

    /**
     * 根据课程ID查询课程介绍
     * @param courseId
     * @return
     */
    CourseDetailVo getCourseDetail(Long courseId);
}
