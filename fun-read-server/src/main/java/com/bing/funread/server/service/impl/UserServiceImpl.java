package com.bing.funread.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bing.funread.common.domain.Activity;
import com.bing.funread.common.domain.Course;
import com.bing.funread.common.domain.User;
import com.bing.funread.common.domain.UserCourse;
import com.bing.funread.common.dto.WeChatLoginCheckDto;
import com.bing.funread.common.dto.WeChatUserInfoDto;
import com.bing.funread.common.enumerate.CertificateType;
import com.bing.funread.common.exception.ServiceException;
import com.bing.funread.common.mapper.ActivityMapper;
import com.bing.funread.common.mapper.CourseMapper;
import com.bing.funread.common.mapper.UserActivityMapper;
import com.bing.funread.common.mapper.UserCourseMapper;
import com.bing.funread.common.mapper.UserMapper;
import com.bing.funread.common.utils.AESUtil;
import com.bing.funread.common.utils.BeanUtil;
import com.bing.funread.common.utils.DateUtil;
import com.bing.funread.request.PageRequest;
import com.bing.funread.request.UserInfoRequest;
import com.bing.funread.request.WeChatLoginRequest;
import com.bing.funread.response.AwardInfoVo;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.response.UserAwardVo;
import com.bing.funread.response.UserInfoVo;
import com.bing.funread.response.UserStudyInfoVo;
import com.bing.funread.server.service.UserService;
import com.bing.funread.server.service.WeChatService;
import com.google.common.collect.Lists;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Description:用户接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:24
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);



    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Autowired
    private UserActivityMapper userActivityMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private WeChatService weChatService;

    @Override
    public User login(WeChatLoginRequest login) {
        WeChatLoginCheckDto checkResult = weChatService.loginCheck(login.getCode());
        byte[] resultByte = AESUtil.decrypt(Base64.decodeBase64(login.getEncryptedData()),
                Base64.decodeBase64(checkResult.getSession_key()),
                Base64.decodeBase64(login.getIv()));
        if (resultByte == null || resultByte.length == 0) {
            throw new ServiceException(ResultCode.FAILED, ResultMessage.FAILED);
        }
        try {
            String userInfo = new String(resultByte, "UTF-8");
            logger.info("微信用户明文信息：{}", userInfo);
            WeChatUserInfoDto userInfoDto = JSONObject.parseObject(userInfo, WeChatUserInfoDto.class);

            User user = BeanUtil.copyBean(userInfoDto, User.class);
            userMapper.insertOrUpdateSelective(user);
            user = userMapper.selectByOpenId(user.getOpenId());
            return user;
        } catch (UnsupportedEncodingException e) {
            logger.error("微信用户信息转码错误：{}", e);
            throw new ServiceException(ResultCode.FAILED, ResultMessage.FAILED);
        }
    }

    @Override
    public UserInfoVo getUserInfo(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        UserInfoVo userInfo = BeanUtil.copyBean(user, UserInfoVo.class);
        if (StringUtils.isNotBlank(user.getName())) {
            userInfo.setNickName(user.getName());
        }
        return userInfo;
    }

    @Override
    public void updateUserInfo(Long userId, UserInfoRequest userInfo) {
        User user = BeanUtil.copyBean(userInfo, User.class);
        user.setId(userId);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional
    @Override
    public boolean bindPhone(Long userId, String phone) {
        // 绑定手机号
        User user = new User();
        user.setId(userId);
        user.setPhone(phone);
        if (userMapper.bindPhone(user) < 1) {
            logger.info("绑定手机号失败，userId:{},phone:{}", userId, phone);
            return false;
        }
        // 更新用户课程
        UserCourse userCourse = new UserCourse();
        userCourse.setUserId(userId);
        userCourse.setPhone(phone);
        userCourseMapper.updateUserIdByPhone(userCourse);
        logger.info("手机号绑定成功，userId:{},phone:{}", userId, phone);
        return true;
    }

    @Override
    public UserStudyInfoVo getUserStudyDetail(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        int studyActivityPoetry = userActivityMapper.selectHasStudyPoetrys(userId);
        int studyCoursePoetry = userCourseMapper.selectHasStudyPoetrys(userId);

        UserStudyInfoVo studyInfoVo = new UserStudyInfoVo();
        studyInfoVo.setStudyDays(DateUtil.getDiffDays(user.getCreateTime(), DateUtil.getCurrentTime()));
        studyInfoVo.setStudyPoetrys(studyActivityPoetry + studyCoursePoetry);
        return studyInfoVo;
    }

    @Override
    public AwardInfoVo getAward(Long userId, PageRequest pageRequest) {
        List<Course> courseList = courseMapper.selectFinishedCourse(userId);
        List<Activity> activityList = activityMapper.selectFinishedActivity(userId);
        if (CollectionUtils.isEmpty(courseList) && CollectionUtils.isEmpty(activityList)) {
            return null;
        }
        int order = 1;
        List<UserAwardVo> awardList = Lists.newArrayList();
        for (Course course : courseList) {
            UserAwardVo vo = new UserAwardVo();
            vo.setAward(course.getName());
            vo.setType(CertificateType.COURSE.getType());
            vo.setOrder(order++);
            awardList.add(vo);
        }
        for (Activity activity : activityList) {
            UserAwardVo vo = new UserAwardVo();
            vo.setAward(activity.getName());
            vo.setType(CertificateType.ACTIVITY.getType());
            vo.setOrder(order++);
            awardList.add(vo);
        }

        AwardInfoVo result = new AwardInfoVo();
        result.setTotal(activityList.size() + activityList.size());

        int start = (pageRequest.getPageIndex() - 1) * pageRequest.getPageSize();
        int end = pageRequest.getPageIndex() * pageRequest.getPageSize();
        if (start > awardList.size()) {
            start = awardList.size();
        }
        if (end > awardList.size()) {
            end = awardList.size();
        }
        result.setUserAwardVoList(awardList.subList(start, end));
        return result;
    }
}
