package com.bing.funread.common.mapper;

import com.bing.funread.common.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int insertOrUpdateSelective(User record);

    int bindPhone(User record);

    User selectByOpenId(@Param("openId")String openId);
}