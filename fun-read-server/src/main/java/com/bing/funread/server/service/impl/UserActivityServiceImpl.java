package com.bing.funread.server.service.impl;

import com.bing.funread.common.constants.CommonConstant;
import com.bing.funread.common.domain.Activity;
import com.bing.funread.common.domain.ActivityPoetry;
import com.bing.funread.common.domain.Poetry;
import com.bing.funread.common.domain.PoetryInfo;
import com.bing.funread.common.domain.UserActivity;
import com.bing.funread.common.domain.UserActivityAudio;
import com.bing.funread.common.dto.ActivityInfoDto;
import com.bing.funread.common.dto.ActivityUserNumDto;
import com.bing.funread.common.exception.ServiceException;
import com.bing.funread.common.mapper.ActivityMapper;
import com.bing.funread.common.mapper.ActivityPoetryMapper;
import com.bing.funread.common.mapper.PoetryInfoMapper;
import com.bing.funread.common.mapper.PoetryMapper;
import com.bing.funread.common.mapper.UserActivityAudioMapper;
import com.bing.funread.common.mapper.UserActivityMapper;
import com.bing.funread.common.utils.BeanUtil;
import com.bing.funread.common.utils.DateUtil;
import com.bing.funread.request.ActivityPoetryRequest;
import com.bing.funread.response.ActivityInfoVo;
import com.bing.funread.response.PoetryVo;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
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
import java.util.Iterator;
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
    private PoetryMapper poetryMapper;

    @Autowired
    private FileService fileService;

    @Override
    public List<ActivityInfoVo> getActivityInfo(Long userId) {
        // 查询活动情况
        List<ActivityInfoDto> activityInfoList = userActivityMapper.selectActivityInfo(userId);
        if (CollectionUtils.isEmpty(activityInfoList)) {
            return null;
        }
        List<ActivityInfoVo> activityInfoVoList = BeanUtil.copyList(activityInfoList, ActivityInfoVo.class);
        // 查询活动参与人数
        List<ActivityUserNumDto> userNumList = activityMapper.selectActivityUserNum();
        for (ActivityInfoVo vo : activityInfoVoList) {
            for (Iterator<ActivityUserNumDto> it = userNumList.iterator(); it.hasNext(); ) {
                ActivityUserNumDto userNum = it.next();
                if (vo.getId().equals(userNum.getActivityId())) {
                    vo.setUserNum(userNum.getUserNum());
                    it.remove();
                    break;
                }
            }
        }
        return activityInfoVoList;
    }

    @Override
    public Boolean getUserActivityStatus(Long userId, Long activityId) {
        UserActivity userActivity = userActivityMapper.selectUserActivityInfo(userId, activityId);
        return userActivity != null;
    }

    @Override
    public void join(Long userId, Long activityId) {
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        if (activity == null || !CommonConstant.YES.equals(activity.getIsValid())) {
            throw new ServiceException(ResultCode.ACTIVITY_INVALID, ResultMessage.ACTIVITY_INVALID);
        }
        if (activity.getStartDate() != null && DateUtil.getCurrentTime().before(activity.getStartDate())) {
            throw new ServiceException(ResultCode.ACTIVITY_NOT_START, ResultMessage.ACTIVITY_NOT_START);
        }
        if (activity.getEndDate() != null && DateUtil.getCurrentTime().after(activity.getEndDate())) {
            throw new ServiceException(ResultCode.ACTIVITY_HAS_END, ResultMessage.ACTIVITY_NOT_START);
        }
        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(userId);
        userActivity.setActivityId(activityId);
        userActivityMapper.insertIgnoreSelective(userActivity);
    }

    @Override
    public UserActivityInfoVo getUserActivityInfo(Long userId, Long activityId) {
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        UserActivity userActivity = userActivityMapper.selectUserActivityInfo(userId, activityId);
        List<Poetry> poetryList = poetryMapper.selectActivityPoetryInfo(activityId);

        UserActivityInfoVo detailVo = new UserActivityInfoVo();
        detailVo.setActivityId(activity.getId());
        detailVo.setName(activity.getName());
        detailVo.setDescribe(activity.getDescribe());
        detailVo.setPoetryNum(activity.getPoetryNum());
        detailVo.setFinishedNum(userActivity.getFinishedNum());
        List<PoetryVo> poetryVoList = BeanUtil.copyList(poetryList, PoetryVo.class);
        for (int i = 0; i < poetryVoList.size(); i++) {
            if (i == userActivity.getFinishedNum()) {
                UserActivityAudio audio = userActivityAudioMapper.selectLastedFinishedTime(userId, activityId);
                if (audio != null && DateUtil.getDiffDays(audio.getCreateTime(), DateUtil.getCurrentTime()) >= 1) {
                    // 第二天解锁下一个任务
                    poetryVoList.get(i).setCurrent(true);
                }
                break;
            }
            poetryVoList.get(i).setFinish(true);
        }
        detailVo.setPoetryList(poetryVoList);
        return detailVo;
    }

    @Override
    public List<UserActivityAudio> upload(Long userId, MultipartFile[] files, ActivityPoetryRequest request) {
        if (files == null || files.length == 0) {
            throw new ServiceException(ResultCode.UPLOAD_FILE_EMPTY, ResultMessage.UPLOAD_FILE_EMPTY);
        }
        // 检查文件数量与诗句数量是否一样
        if (files.length != request.getPoetryInfoIdList().size()) {
            throw new ServiceException(ResultCode.UPLOAD_FILE_POETRY_NUM_DIFF, ResultMessage.UPLOAD_FILE_POETRY_NUM_DIFF);
        }
        // 检查活动ID、诗词ID、诗句ID是否有效
        UserActivity userActivity = userActivityMapper.selectUserActivityInfo(userId, request.getActivityId());
        if (userActivity == null) {
            throw new ServiceException(ResultCode.USER_ACTIVITY_ERROR, ResultMessage.USER_ACTIVITY_ERROR);
        }
        ActivityPoetry activityPoetry = activityPoetryMapper.selectByActivityAndPoetry(request.getActivityId(), request.getPoetryId());
        if (activityPoetry == null) {
            throw new ServiceException(ResultCode.ACTIVITY_POETRY_ERROR, ResultMessage.ACTIVITY_POETRY_ERROR);
        }
        List<PoetryInfo> poetryInfoList = poetryInfoMapper.selectByPoetryId(request.getPoetryId());
        if (CollectionUtils.isEmpty(poetryInfoList)) {
            throw new ServiceException(ResultCode.POETRY_INFO_EMPTY, ResultMessage.POETRY_INFO_EMPTY);
        }
        if (poetryInfoList.size() != request.getPoetryInfoIdList().size()) {
            throw new ServiceException(ResultCode.NOT_ALL_POETRY_FILE, ResultMessage.NOT_ALL_POETRY_FILE);
        }
        List<Long> poetryInfoIdList = Lists.newArrayList();
        for (PoetryInfo poetryInfo : poetryInfoList) {
            poetryInfoIdList.add(poetryInfo.getId());
        }
        for (Long poetryInfoId : request.getPoetryInfoIdList()) {
            if (!poetryInfoIdList.contains(poetryInfoId)) {
                throw new ServiceException(ResultCode.POETRY_INFO_NOT_EXISTS, ResultMessage.POETRY_INFO_NOT_EXISTS);
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

        return userActivityAudioList;
    }

    @Transactional
    @Override
    public void saveUploadInfo(List<UserActivityAudio> userActivityAudioList) {
        if (CollectionUtils.isEmpty(userActivityAudioList)) {
            return;
        }
        // 更新数据库
        for (UserActivityAudio userActivityAudio : userActivityAudioList) {
            userActivityAudioMapper.insertOrUpdateSelective(userActivityAudio);
        }
        // 更新用户活动诗词已完成数量
        userActivityMapper.updateActivityFinishedNum(userActivityAudioList.get(0).getUserActivityId());
    }

    private String createFileDir(ActivityPoetryRequest request, Long userId, String baseDir) {
        return baseDir + File.separator +
                userId + File.separator +
                request.getActivityId() + File.separator +
                request.getPoetryId() + File.separator;
    }
}
