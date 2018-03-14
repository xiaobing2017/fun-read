package com.bing.funread.server.service;

import com.bing.funread.response.ExercisesInfoVo;

import java.util.List;

/**
 * Description:练习题接口
 * Author: zhangfusheng
 * Date: 2018/3/14 上午11:46
 */
public interface ExercisesService {

    /**
     * 查询诗词练习题列表
     * @param poetryId
     * @return
     */
    List<ExercisesInfoVo> getExercises(Long poetryId);

    /**
     * 查询课程期末练习题
     * @param courseId
     * @return
     */
    List<ExercisesInfoVo> getFinalTest(Long courseId);
}
