package com.bing.funread.server.service;

import com.bing.funread.common.domain.UserActivityAudio;
import com.bing.funread.request.PageRequest;
import com.bing.funread.response.ActivityInfoVo;
import com.bing.funread.response.ReadInfoVo;
import com.bing.funread.response.UserActivityInfoVo;
import com.bing.funread.response.UserStudyInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Description:用户活动接口
 * Author: zhangfusheng
 * Date: 2018/3/8 下午3:39
 */
public interface UserActivityService {

    /**
     * 查询活动列表
     * @param userId
     * @return
     */
    List<ActivityInfoVo> getActivityInfo(Long userId);

    /**
     * 检查用户是否参加了活动
     * @param userId
     * @param activityId
     * @return
     */
    Boolean getUserActivityStatus(Long userId, Long activityId);

    /**
     * 用户参加活动
     * @param userId
     * @param activityId
     */
    void join(Long userId, Long activityId);

    /**
     * 查询用户活动信息
     * @param userId
     * @param activityId
     * @param pageRequest
     * @return
     */
    UserActivityInfoVo getUserActivityInfo(Long userId, Long activityId, PageRequest pageRequest);

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
    List<ReadInfoVo> getReadInfo(Long userId, Long courseId, Long poetryId);

    /**
     * 单文件上传
     * @param userId
     * @param activityId
     * @param poetryId
     * @param poetryInfoId
     * @param file
     * @return
     */
    UserActivityAudio upload(Long userId, Long activityId, Long poetryId, Long poetryInfoId, MultipartFile file);

    /**
     * 保存跟读文件信息
     * @param audio
     */
    void saveAudio(UserActivityAudio audio);
}

