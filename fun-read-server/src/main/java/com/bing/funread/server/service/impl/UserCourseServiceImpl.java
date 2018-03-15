package com.bing.funread.server.service.impl;

import com.bing.funread.common.constants.CommonConstant;
import com.bing.funread.common.domain.Course;
import com.bing.funread.common.domain.CoursePoetry;
import com.bing.funread.common.domain.Poetry;
import com.bing.funread.common.domain.PoetryInfo;
import com.bing.funread.common.domain.User;
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
import com.bing.funread.common.utils.DateUtil;
import com.bing.funread.request.CoursePoetryRequest;
import com.bing.funread.response.CourseDetailVo;
import com.bing.funread.response.PoetryVo;
import com.bing.funread.response.ReadInfoVo;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.response.UserCourseInfoVo;
import com.bing.funread.response.UserStudyInfoVo;
import com.bing.funread.server.service.FileService;
import com.bing.funread.server.service.UserCourseService;
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
                if (vo.getCourseId().equals(userNum.getCourserId())) {
                    vo.setUserNum(userNum.getUserNum());
                    it.remove();
                    break;
                }
            }
        }
        return voList;
    }

    @Override
    public CourseDetailVo getUserCourseDetail(Long userId, Long courseId) {
        Course course = courseMapper.selectByPrimaryKey(courseId);
        UserCourse userCourse = userCourseMapper.selectByUserAndCourse(userId, courseId);
        List<Poetry> poetryList = poetryMapper.selectCoursePoetryInfo(courseId);

        CourseDetailVo detailVo = new CourseDetailVo();
        detailVo.setCourseId(course.getId());
        detailVo.setName(course.getName());
        detailVo.setDescribe(course.getDescribe());
        detailVo.setPoetryNum(course.getPoetryNum());
        detailVo.setFinishedNum(userCourse.getFinishedNum());
        List<PoetryVo> poetryVoList = BeanUtil.copyList(poetryList, PoetryVo.class);
        for (int i = 0; i < poetryVoList.size(); i++) {
            if (i == userCourse.getFinishedNum()) {
                poetryVoList.get(i).setCurrent(true);
                break;
            }
            poetryVoList.get(i).setFinish(true);
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

    @Override
    public ReadInfoVo getReadInfo(Long userId, Long courseId, Long poetryId) {
        ReadInfoDto readInfo = userCourseMapper.selectReadInfo(userId, courseId, poetryId);
        return BeanUtil.copyBean(readInfo, ReadInfoVo.class);
    }

    @Override
    public List<UserCourseAudio> upload(Long userId, MultipartFile[] files, CoursePoetryRequest request) {
        if (files == null || files.length == 0) {
            throw new ServiceException(ResultCode.UPLOAD_FILE_EMPTY, ResultMessage.UPLOAD_FILE_EMPTY);
        }
        // 检查文件数量与诗句数量是否一样
        if (files.length != request.getPoetryInfoIdList().size()) {
            throw new ServiceException(ResultCode.UPLOAD_FILE_POETRY_NUM_DIFF, ResultMessage.UPLOAD_FILE_POETRY_NUM_DIFF);
        }
        // 检查课程ID、诗词ID、诗句ID是否有效
        UserCourse userCourse = userCourseMapper.selectByUserAndCourse(userId, request.getCourseId());
        if (userCourse == null) {
            throw new ServiceException(ResultCode.USER_COURSE_ERROR, ResultMessage.USER_COURSE_ERROR);
        }
        CoursePoetry coursePoetry = coursePoetryMapper.selectByCourseAndPoetry(request.getCourseId(), request.getPoetryId());
        if (coursePoetry == null) {
            throw new ServiceException(ResultCode.COURSE_POETRY_ERROR, ResultMessage.COURSE_POETRY_ERROR);
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
        List<UserCourseAudio> userCourseAudioList = Lists.newArrayList();
        // 保存文件
        int i = 0;
        for (MultipartFile file : files) {
            String saveDir = createFileDir(request, userId, CommonConstant.FILE_DIR_COURSE);
            Long poetryInfoId = request.getPoetryInfoIdList().get(i++);
            String audioUrl = fileService.upload(file, saveDir, poetryInfoId.toString());
            UserCourseAudio userCourseAudio = new UserCourseAudio();
            userCourseAudio.setUserCourseId(userCourse.getId());
            userCourseAudio.setPoetryId(request.getPoetryId());
            userCourseAudio.setPoetryInfoId(poetryInfoId);
            userCourseAudio.setAudioUrl(audioUrl);
            userCourseAudioList.add(userCourseAudio);
        }

        return userCourseAudioList;
    }

    @Transactional
    @Override
    public void saveUploadInfo(List<UserCourseAudio> userCourseAudioList) {
        if (CollectionUtils.isEmpty(userCourseAudioList)) {
            return;
        }
        // 更新数据库
        for (UserCourseAudio userCourseAudio : userCourseAudioList) {
            userCourseAudioMapper.insertOrUpdateSelective(userCourseAudio);
        }
        // 更新用户课程诗词已完成数量
        userCourseMapper.updateCourseFinishedNum(userCourseAudioList.get(0).getUserCourseId());
    }

    private String createFileDir(CoursePoetryRequest request, Long userId, String baseDir) {
        return baseDir + File.separator +
                userId + File.separator +
                request.getCourseId() + File.separator +
                request.getPoetryId() + File.separator;
    }
}
