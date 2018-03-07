package com.bing.funread.server.service.impl;

import com.bing.funread.common.domain.User;
import com.bing.funread.common.mapper.UserMapper;
import com.bing.funread.request.WeChatRegRequest;
import com.bing.funread.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:用户接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User weChatRegister(WeChatRegRequest request) {
        User user = new User();
        user.setUnionId(request.getUnionid());
        user.setOpenId(request.getOpenid());
        user.setWeChatName(request.getNickname());
        user.setSex(request.getSex());
        user.setCountry(request.getCounty());
        user.setProvince(request.getProvince());
        user.setCity(request.getCity());
        user.setHeadImgUrl(request.getHeadimgurl());
        userMapper.insertSelective(user);
        return user;
    }
}
