package com.bing.funread.server.service.impl;

import com.bing.funread.common.constants.CommonConstant;
import com.bing.funread.common.domain.Course;
import com.bing.funread.common.domain.CoursePoetry;
import com.bing.funread.common.domain.Poetry;
import com.bing.funread.common.domain.PoetryInfo;
import com.bing.funread.common.domain.User;
import com.bing.funread.common.domain.UserCourse;
import com.bing.funread.common.domain.UserCourseAudio;
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
import com.bing.funread.response.UserCourseInfoVo;
import com.bing.funread.response.UserStudyInfoVo;
import com.bing.funread.server.service.FileService;
import com.bing.funread.server.service.UserCourseService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
        List<UserCourseInfoDto> courseInfoList = courseMapper.selectUserCourseInfo(userId);
        return BeanUtil.copyList(courseInfoList, UserCourseInfoVo.class);
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
    public void upload(Long userId, MultipartFile[] files, CoursePoetryRequest request) {
        // 检查文件数量与诗句数量是否一样
        if (files == null || files.length == 0 || files.length != request.getPoetryInfoIdList().size()) {
            throw new ServiceException("", "上传信息不匹配");
        }
        // 检查课程ID、诗词ID、诗句ID是否有效
        UserCourse userCourse = userCourseMapper.selectByUserAndCourse(userId, request.getCourseId());
        if (userCourse == null) {
            throw new ServiceException("", "用户课程不存在");
        }
        CoursePoetry coursePoetry = coursePoetryMapper.selectByCourseAndPoetry(request.getCourseId(), request.getPoetryId());
        if (coursePoetry == null) {
            throw new ServiceException("", "课程诗词不存在");
        }
        List<PoetryInfo> poetryInfoList = poetryInfoMapper.selectByPoetryId(request.getPoetryId());
        if (CollectionUtils.isEmpty(poetryInfoList)) {
            throw new ServiceException("", "诗句不存在");
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
        // 更新数据库
        for (UserCourseAudio userCourseAudio : userCourseAudioList) {
            userCourseAudioMapper.insertSelective(userCourseAudio);
        }
    }

    private String createFileDir(CoursePoetryRequest request, Long userId, String baseDir) {
        return File.separator + baseDir +
                File.separator + userId +
                File.separator + request.getCourseId() +
                File.separator + request.getPoetryId();
    }
}
