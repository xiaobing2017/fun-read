package com.bing.funread.server.service;

import com.bing.funread.common.domain.UserCourseAudio;
import com.bing.funread.request.PageRequest;
import com.bing.funread.response.CourseDetailVo;
import com.bing.funread.response.ReadInfoVo;
import com.bing.funread.response.UserCourseInfoVo;
import org.springframework.web.multipart.MultipartFile;

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
    List<UserCourseInfoVo> getUserCourseInfo(Long userId);

    /**
     * 用户课程详情
     * @param userId
     * @param courseId
     * @param pageRequest
     * @return
     */
    CourseDetailVo getUserCourseDetail(Long userId, Long courseId, PageRequest pageRequest);

    /**
     * 查询用户课程诗词跟读信息
     * @param userId
     * @param courseId
     * @param poetryId
     * @return
     */
    List<ReadInfoVo> getReadInfo(Long userId, Long courseId, Long poetryId);

    /**
     * 单文件上传
     * @param userId
     * @param courseId
     * @param poetryId
     * @param poetryInfoId
     * @param file
     * @return
     */
    UserCourseAudio upload(Long userId, Long courseId, Long poetryId, Long poetryInfoId, MultipartFile file);

    /**
     * 保存跟读文件信息
     * @param audio
     */
    void saveAudio(UserCourseAudio audio);
}
