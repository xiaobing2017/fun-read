package com.bing.funread.server.service.impl;

import com.bing.funread.common.domain.Course;
import com.bing.funread.common.domain.Poetry;
import com.bing.funread.common.domain.User;
import com.bing.funread.common.domain.UserCourse;
import com.bing.funread.common.dto.CourseInfoDto;
import com.bing.funread.common.mapper.CourseMapper;
import com.bing.funread.common.mapper.PoetryMapper;
import com.bing.funread.common.mapper.UserCourseMapper;
import com.bing.funread.common.mapper.UserMapper;
import com.bing.funread.common.utils.BeanUtil;
import com.bing.funread.common.utils.DateUtil;
import com.bing.funread.response.CourseDetailVo;
import com.bing.funread.response.CourseInfoVo;
import com.bing.funread.response.PoetryVo;
import com.bing.funread.response.UserStudyInfoVo;
import com.bing.funread.server.service.UserCourseService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description:用户课程接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/7 下午7:46
 */
@Service
public class UserCourseServiceImpl implements UserCourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private PoetryMapper poetryMapper;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<CourseInfoVo> getUserCourseInfo(Long userId) {
        List<CourseInfoDto> courseInfoList = courseMapper.selectUserCourseInfo(userId);
        return BeanUtil.copyList(courseInfoList, CourseInfoVo.class);
    }

    @Override
    public CourseDetailVo getUserCourseDetail(Long userId, Long courseId) {
        Course course = courseMapper.selectByPrimaryKey(courseId);
        UserCourse userCourse = userCourseMapper.selectByUserAndCourse(userId, courseId);
        List<Poetry> poetryList = poetryMapper.selectCoursePoetryInfo(courseId);

        CourseDetailVo detailVo = new CourseDetailVo();
        detailVo.setName(course.getName());
        List<PoetryVo> poetryVoList = BeanUtil.copyList(poetryList, PoetryVo.class);
        for (int i = 0; i < poetryVoList.size(); i++) {
            if (i == userCourse.getUnlockNum()) break;
            poetryVoList.get(i).setUnlock(true);
        }
        detailVo.setPoetryList(poetryVoList);
        return detailVo;
    }

    @Override
    public UserStudyInfoVo getUserStudyDetail(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        int studyPoetrys = userCourseMapper.selectHasStudyPoetrys(userId);

        UserStudyInfoVo studyInfoVo = new UserStudyInfoVo();
        studyInfoVo.setStudyDays(DateUtil.getDiffDays(user.getCreateTime(), DateUtil.getCurrentTime()));
        studyInfoVo.setStudyPoetrys(studyPoetrys);
        return studyInfoVo;
    }
}
