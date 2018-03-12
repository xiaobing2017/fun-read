package com.bing.funread.server.service;

import com.bing.funread.request.ActivityPoetryRequest;
import com.bing.funread.response.UserActivityInfoVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:用户活动接口
 * Author: zhangfusheng
 * Date: 2018/3/8 下午3:39
 */
public interface UserActivityService {

    /**
     * 查询用户活动信息
     * @param userId
     * @param activityId
     * @return
     */
    UserActivityInfoVo getUserActivityInfo(Long userId, Long activityId);

    /**
     * 保存跟读文件
     * @param userId
     * @param files
     * @param request
     */
    void upload(Long userId, MultipartFile[] files, ActivityPoetryRequest request);
}

