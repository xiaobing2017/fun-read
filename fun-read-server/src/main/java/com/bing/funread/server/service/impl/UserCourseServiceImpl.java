package com.bing.funread.server.service.impl;

import com.bing.funread.common.constants.CommonConstant;
import com.bing.funread.common.domain.Course;
import com.bing.funread.common.domain.CoursePoetry;
import com.bing.funread.common.domain.Poetry;
import com.bing.funread.common.domain.PoetryInfo;
import com.bing.funread.common.domain.UserCourse;
import com.bing.funread.common.domain.UserCourseAudio;
import com.bing.funread.common.dto.CourseUserNumDto;
import com.bing.funread.common.dto.ReadInfoDto;
import com.bing.funread.common.dto.UserCourseInfoDto;
import com.bing.funread.common.exception.ServiceException;
import com.bing.funread.common.mapper.CourseMapper;
import com.bing.funread.common.mapper.CoursePoetryMapper;
import com.bing.funread.common.mapper.PoetryInfoMapper;
import com.bing.funread.common.mapper.PoetryMapper;
import com.bing.funread.common.mapper.UserCourseAudioMapper;
import com.bing.funread.common.mapper.UserCourseMapper;
import com.bing.funread.common.mapper.UserMapper;
import com.bing.funread.common.utils.BeanUtil;
import com.bing.funread.request.PageRequest;
import com.bing.funread.response.CourseDetailVo;
import com.bing.funread.response.PoetryVo;
import com.bing.funread.response.ReadInfoVo;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.response.UserCourseInfoVo;
import com.bing.funread.server.service.FileService;
import com.bing.funread.server.service.UserCourseService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Description:用户课程接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/7 下午7:46
 */
@Service
public class UserCourseServiceImpl implements UserCourseService {

    private static final Logger logger = LoggerFactory.getLogger(UserCourseServiceImpl.class);

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private PoetryMapper poetryMapper;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CoursePoetryMapper coursePoetryMapper;

    @Autowired
    private PoetryInfoMapper poetryInfoMapper;

    @Autowired
    private UserCourseAudioMapper userCourseAudioMapper;

    @Autowired
    private FileService fileService;

    @Override
    public List<UserCourseInfoVo> getUserCourseInfo(Long userId) {
        // 查询当前用户课程情况
        List<UserCourseInfoDto> courseInfoList = courseMapper.selectUserCourseInfo(userId);
        if (CollectionUtils.isEmpty(courseInfoList)) {
            logger.info("用户：{} 目前没有课程", userId);
            return null;
        }
        List<UserCourseInfoVo> voList = BeanUtil.copyList(courseInfoList, UserCourseInfoVo.class);
        // 查询当前用户课程参与人数
        List<Long> courseIdList = Lists.newArrayList();
        for (UserCourseInfoVo vo : voList) {
            courseIdList.add(vo.getCourseId());
        }
        List<CourseUserNumDto> userNumList = userCourseMapper.selectUserNumByCourseIdList(courseIdList);
        for (UserCourseInfoVo vo : voList) {
            for (Iterator<CourseUserNumDto> it = userNumList.iterator(); it.hasNext(); ) {
                CourseUserNumDto userNum = it.next();
                if (vo.getCourseId().equals(userNum.getCourseId())) {
                    vo.setUserNum(userNum.getUserNum());
                    it.remove();
                    break;
                }
            }
        }
        return voList;
    }

    @Override
    public List<String> getFinishedCourseName(Long userId) {
        List<Course> courseList = courseMapper.selectFinishedCourse(userId);
        if (CollectionUtils.isEmpty(courseList)) {
            return null;
        }
        List<String> courseNameList = Lists.newArrayList();
        courseList.forEach(course -> courseNameList.add(course.getName()));
        return courseNameList;
    }

    @Override
    public CourseDetailVo getUserCourseDetail(Long userId, Long courseId, PageRequest pageRequest) {
        Course course = courseMapper.selectByPrimaryKey(courseId);
        UserCourse userCourse = userCourseMapper.selectByUserAndCourse(userId, courseId);
        // 分页查询
        PageHelper.startPage(pageRequest.getPageIndex(), pageRequest.getPageSize(), false);
        List<Poetry> poetryList = poetryMapper.selectCoursePoetryInfo(courseId);

        CourseDetailVo detailVo = new CourseDetailVo();
        detailVo.setCourseId(course.getId());
        detailVo.setName(course.getName());
        detailVo.setDescribe(course.getDescribe());
        detailVo.setPoetryNum(course.getPoetryNum());
        detailVo.setFinishedNum(userCourse.getFinishedNum());
        List<PoetryVo> poetryVoList = BeanUtil.copyList(poetryList, PoetryVo.class);
        int order = (pageRequest.getPageIndex() - 1) * pageRequest.getPageSize();
        for (PoetryVo poetryVo : poetryVoList) {
            poetryVo.setOrder(++order);
            if (order <= userCourse.getFinishedNum()) {
                poetryVo.setFinish(true);
            } else if (order == userCourse.getFinishedNum() + 1) {
                poetryVo.setCurrent(true);
            }
        }
        detailVo.setPoetryList(poetryVoList);
        return detailVo;
    }

    @Override
    public List<ReadInfoVo> getReadInfo(Long userId, Long courseId, Long poetryId) {
        List<ReadInfoDto> readInfoList = userCourseMapper.selectReadInfo(userId, courseId, poetryId);
        return BeanUtil.copyList(readInfoList, ReadInfoVo.class);
    }

    @Override
    public UserCourseAudio upload(Long userId, Long courseId, Long poetryId, Long poetryInfoId, MultipartFile file) {
        // 检查课程ID、诗词ID、诗句ID是否有效
        UserCourse userCourse = userCourseMapper.selectByUserAndCourse(userId, courseId);
        if (userCourse == null) {
            throw new ServiceException(ResultCode.USER_COURSE_ERROR, ResultMessage.USER_COURSE_ERROR);
        }
        CoursePoetry coursePoetry = coursePoetryMapper.selectByCourseAndPoetry(courseId, poetryId);
        if (coursePoetry == null) {
            throw new ServiceException(ResultCode.COURSE_POETRY_ERROR, ResultMessage.COURSE_POETRY_ERROR);
        }
        PoetryInfo poetryInfo = poetryInfoMapper.selectByPrimaryKey(poetryInfoId);
        if (poetryInfo == null) {
            throw new ServiceException(ResultCode.POETRY_INFO_NOT_EXISTS, ResultMessage.POETRY_INFO_NOT_EXISTS);
        }
        // 保存文件
        String saveDir = createFileDir(userId, courseId, poetryId, CommonConstant.FILE_DIR_COURSE);
        String audioUrl = fileService.upload(file, saveDir, poetryInfoId.toString());

        UserCourseAudio userCourseAudio = new UserCourseAudio();
        userCourseAudio.setUserCourseId(userCourse.getId());
        userCourseAudio.setPoetryId(poetryId);
        userCourseAudio.setPoetryInfoId(poetryInfoId);
        userCourseAudio.setAudioUrl(audioUrl);
        return userCourseAudio;
    }

    @Transactional
    @Override
    public void saveAudio(UserCourseAudio audio) {
        UserCourseAudio record = userCourseAudioMapper.selectByUniqueKey(audio.getUserCourseId(),
                audio.getPoetryId(), audio.getPoetryInfoId());
        if (record == null) {
            // 插入数据库
            userCourseAudioMapper.insertSelective(audio);
            // 更新用户课程诗词已完成数量
            userCourseMapper.updateCourseFinishedNum(audio.getUserCourseId());
        }
    }

    private String createFileDir(Long userId, Long courseId, Long poetryId, String baseDir) {
        return baseDir + File.separator +
                userId + File.separator +
                courseId + File.separator +
                poetryId + File.separator;
    }
}
