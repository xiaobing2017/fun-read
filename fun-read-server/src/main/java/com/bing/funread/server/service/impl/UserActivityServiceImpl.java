package com.bing.funread.server.service.impl;

import com.bing.funread.common.constants.CommonConstant;
import com.bing.funread.common.domain.Activity;
import com.bing.funread.common.domain.ActivityPoetry;
import com.bing.funread.common.domain.PoetryInfo;
import com.bing.funread.common.domain.UserActivity;
import com.bing.funread.common.domain.UserActivityAudio;
import com.bing.funread.common.exception.ServiceException;
import com.bing.funread.common.mapper.ActivityMapper;
import com.bing.funread.common.mapper.ActivityPoetryMapper;
import com.bing.funread.common.mapper.PoetryInfoMapper;
import com.bing.funread.common.mapper.UserActivityAudioMapper;
import com.bing.funread.common.mapper.UserActivityMapper;
import com.bing.funread.request.ActivityPoetryRequest;
import com.bing.funread.response.UserActivityInfoVo;
import com.bing.funread.server.service.FileService;
import com.bing.funread.server.service.UserActivityService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Description:用户活动接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/8 下午3:39
 */
@Service
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private UserActivityMapper userActivityMapper;

    @Autowired
    private ActivityPoetryMapper activityPoetryMapper;

    @Autowired
    private PoetryInfoMapper poetryInfoMapper;

    @Autowired
    private UserActivityAudioMapper userActivityAudioMapper;


    @Autowired
    private FileService fileService;

    @Override
    public UserActivityInfoVo getUserActivityInfo(Long userId, Long activityId) {
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        UserActivity userActivity = userActivityMapper.selectUserActivityInfo(userId, activityId);
        List<ActivityPoetry> activityPoetryList = activityPoetryMapper.selectByActivityId(activityId);

        UserActivityInfoVo activityInfoVo = new UserActivityInfoVo();
        activityInfoVo.setFinishNum(userActivity.getFinishedNum());
        activityInfoVo.setUnFinishNum(activity.getPoetryNum() - userActivity.getFinishedNum());
        activityInfoVo.setPoetryId(activityPoetryList.get(userActivity.getFinishedNum()).getPoetryId());
        return null;
    }

    @Transactional
    @Override
    public void upload(Long userId, MultipartFile[] files, ActivityPoetryRequest request) {
        // 检查文件数量与诗句数量是否一样
        if (files == null || files.length == 0 || files.length != request.getPoetryInfoIdList().size()) {
            throw new ServiceException("", "上传信息不匹配");
        }
        // 检查活动ID、诗词ID、诗句ID是否有效
        UserActivity userActivity = userActivityMapper.selectUserActivityInfo(userId, request.getActivityId());
        if (userActivity == null) {
            throw new ServiceException("", "用户活动不存在");
        }
        ActivityPoetry activityPoetry = activityPoetryMapper.selectByActivityAndPoetry(request.getActivityId(), request.getPoetryId());
        if (activityPoetry == null) {
            throw new ServiceException("", "活动诗词不存在");
        }
        List<PoetryInfo> poetryInfoList = poetryInfoMapper.selectByPoetryId(request.getPoetryId());
        if (CollectionUtils.isEmpty(poetryInfoList)) {
            throw new ServiceException("", "诗句不存在");
        }
        if (poetryInfoList.size() != request.getPoetryInfoIdList().size()) {
            throw new ServiceException("", "上传文件不完整");
        }
        List<Long> poetryInfoIdList = Lists.newArrayList();
        for (PoetryInfo poetryInfo : poetryInfoList) {
            poetryInfoIdList.add(poetryInfo.getId());
        }
        for (Long poetryInfoId : request.getPoetryInfoIdList()) {
            if (!poetryInfoIdList.contains(poetryInfoId)) {
                throw new ServiceException("", "诗句ID不存在");
            }
        }
        // 创建用户课程音频文件保存对象
        List<UserActivityAudio> userActivityAudioList = Lists.newArrayList();
        // 保存文件
        int i = 0;
        for (MultipartFile file : files) {
            String saveDir = createFileDir(request, userId, CommonConstant.FILE_DIR_ACTIVITY);
            Long poetryInfoId = request.getPoetryInfoIdList().get(i++);
            String audioUrl = fileService.upload(file, saveDir, poetryInfoId.toString());
            UserActivityAudio userActivityAudio = new UserActivityAudio();
            userActivityAudio.setUserActivityId(userActivity.getId());
            userActivityAudio.setPoetryId(request.getPoetryId());
            userActivityAudio.setPoetryInfoId(poetryInfoId);
            userActivityAudio.setAudioUrl(audioUrl);
            userActivityAudioList.add(userActivityAudio);
        }
        // 更新数据库
        for (UserActivityAudio userActivityAudio : userActivityAudioList) {
            userActivityAudioMapper.insertOrUpdateSelective(userActivityAudio);
        }
        // 更新用户课程诗词已完成数量
        userActivityMapper.updateActivityFinishedNum(userActivity.getId());
    }

    private String createFileDir(ActivityPoetryRequest request, Long userId, String baseDir) {
        return baseDir + File.separator +
                userId + File.separator +
                request.getActivityId() + File.separator +
                request.getPoetryId() + File.separator;
    }
}
