package com.bing.funread.server.service;

import com.bing.funread.common.domain.UserCourseAudio;
import com.bing.funread.request.CoursePoetryRequest;
import com.bing.funread.response.CourseDetailVo;
import com.bing.funread.response.ReadInfoVo;
import com.bing.funread.response.UserCourseInfoVo;
import com.bing.funread.response.UserStudyInfoVo;
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
     * @return
     */
    CourseDetailVo getUserCourseDetail(Long userId, Long courseId);

    /**
     * 查询用户学习情况
     * @param userId
     * @return
     */
    UserStudyInfoVo getUserStudyDetail(Long userId);

    /**
     * 查询用户课程诗词跟读信息
     * @param userId
     * @param courseId
     * @param poetryId
     * @return
     */
    ReadInfoVo getReadInfo(Long userId, Long courseId, Long poetryId);

    /**
     * 保存跟读文件
     * @param userId
     * @param files
     * @param request
     * @return
     */
    List<UserCourseAudio> upload(Long userId, MultipartFile[] files, CoursePoetryRequest request);

    /**
     * 保存跟读信息
     * @param userCourseAudioList
     */
    void saveUploadInfo(List<UserCourseAudio> userCourseAudioList);
}
