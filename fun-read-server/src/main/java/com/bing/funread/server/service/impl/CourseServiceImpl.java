package com.bing.funread.server.service.impl;

import com.bing.funread.common.domain.Course;
import com.bing.funread.common.domain.Poetry;
import com.bing.funread.common.dto.UserCourseInfoDto;
import com.bing.funread.common.mapper.CourseMapper;
import com.bing.funread.common.mapper.PoetryMapper;
import com.bing.funread.common.utils.BeanUtil;
import com.bing.funread.response.CourseDetailVo;
import com.bing.funread.response.UserCourseInfoVo;
import com.bing.funread.response.PoetryVo;
import com.bing.funread.server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:课程接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/7 下午5:16
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private PoetryMapper poetryMapper;

    @Override
    public List<UserCourseInfoVo> getCourseInfo() {
        List<UserCourseInfoDto> courseInfoList = courseMapper.selectAllCourseInfo();
        return BeanUtil.copyList(courseInfoList, UserCourseInfoVo.class);
    }

    @Override
    public CourseDetailVo getCourseDetail(Long courseId) {
        Course course = courseMapper.selectByPrimaryKey(courseId);
        List<Poetry> poetryList = poetryMapper.selectCoursePoetryInfo(courseId);

        CourseDetailVo detailVo = new CourseDetailVo();
        detailVo.setName(course.getName());
        detailVo.setDescribe(course.getDescribe());
        detailVo.setPoetryList(BeanUtil.copyList(poetryList, PoetryVo.class));
        return detailVo;
    }
}
