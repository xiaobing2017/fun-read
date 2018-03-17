package com.bing.funread.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bing.funread.common.domain.User;
import com.bing.funread.common.domain.UserCourse;
import com.bing.funread.common.dto.WeChatLoginCheckDto;
import com.bing.funread.common.dto.WeChatUserInfoDto;
import com.bing.funread.common.exception.ServiceException;
import com.bing.funread.common.mapper.UserCourseMapper;
import com.bing.funread.common.mapper.UserMapper;
import com.bing.funread.common.utils.AESUtil;
import com.bing.funread.common.utils.BeanUtil;
import com.bing.funread.common.utils.HttpUtil;
import com.bing.funread.request.UserInfoRequest;
import com.bing.funread.request.WeChatLoginRequest;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.response.UserInfoVo;
import com.bing.funread.server.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;

/**
 * Description:用户接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:24
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${weChat.app.id}")
    private String appId;

    @Value("${weChat.app.secret}")
    private String appSecret;

    @Value("${weChat.login.check.url}")
    private String checkUrl;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Override
    public User login(WeChatLoginRequest login) {
        String url = String.format(checkUrl, appId, appSecret, login.getCode());
        WeChatLoginCheckDto checkResult = HttpUtil.httpsRequest(url, RequestMethod.GET.name(), null, WeChatLoginCheckDto.class);
        if (checkResult == null || StringUtils.isBlank(checkResult.getSession_key())) {
            throw new ServiceException(ResultCode.FAILED, ResultMessage.FAILED);
        }
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
            return user;
        } catch (UnsupportedEncodingException e) {
            logger.error("微信用户信息转码错误：{}", e);
            throw new ServiceException(ResultCode.FAILED, ResultMessage.FAILED);
        }
    }

    @Override
    public UserInfoVo getUserInfo(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return BeanUtil.copyBean(user, UserInfoVo.class);
    }

    @Override
    public void updateUserInfo(Long userId, UserInfoRequest userInfo) {
        if (!userInfo.equals(userId)) {
            throw new ServiceException(ResultCode.USER_ID_ERROR, ResultMessage.USER_ID_ERROR);
        }
        User user = BeanUtil.copyBean(userInfo, User.class);
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
}
