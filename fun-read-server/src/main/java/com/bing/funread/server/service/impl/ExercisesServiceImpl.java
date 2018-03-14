package com.bing.funread.server.service.impl;

import com.bing.funread.common.domain.Exercises;
import com.bing.funread.common.domain.ExercisesOptions;
import com.bing.funread.common.mapper.ExercisesMapper;
import com.bing.funread.common.mapper.ExercisesOptionsMapper;
import com.bing.funread.common.utils.BeanUtil;
import com.bing.funread.response.ExercisesInfoVo;
import com.bing.funread.response.ExercisesOptionsVo;
import com.bing.funread.server.service.ExercisesService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;

/**
 * Description:练习题接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/14 上午11:47
 */
@Service
public class ExercisesServiceImpl implements ExercisesService {

    @Autowired
    private ExercisesMapper exercisesMapper;

    @Autowired
    private ExercisesOptionsMapper exercisesOptionsMapper;

    @Override
    public List<ExercisesInfoVo> getExercises(Long poetryId) {
        List<Exercises> exercisesList = exercisesMapper.selectByPoetryId(poetryId);
        List<ExercisesOptions> optionsList = exercisesOptionsMapper.selectByPoetryId(poetryId);

        if (CollectionUtils.isEmpty(exercisesList) || CollectionUtils.isEmpty(optionsList)) {
            return null;
        }

        List<ExercisesInfoVo> exercisesInfoList = BeanUtil.copyList(exercisesList, ExercisesInfoVo.class);
        for (ExercisesInfoVo exercisesInfo : exercisesInfoList) {
            for (Iterator<ExercisesOptions> it = optionsList.iterator(); it.hasNext(); ) {
                ExercisesOptions options = it.next();
                if (options.getExercisesId() != exercisesInfo.getId()) continue;
                ExercisesOptionsVo optionsVo = BeanUtil.copyBean(options, ExercisesOptionsVo.class);
                List<ExercisesOptionsVo> optionsVoList = exercisesInfo.getExercisesOptionsList();
                if (optionsVoList == null) {
                    optionsVoList = Lists.newArrayList();
                    exercisesInfo.setExercisesOptionsList(optionsVoList);
                }
                optionsVoList.add(optionsVo);
                it.remove();
            }
        }
        return exercisesInfoList;
    }
}
