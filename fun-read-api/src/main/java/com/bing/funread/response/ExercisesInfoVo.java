package com.bing.funread.response;

import com.bing.funread.annotation.Encryption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Description:练习题信息
 * Author: zhangfusheng
 * Date: 2018/3/14 上午11:58
 */
@Encryption(value = {"audioUrl"})
@ApiModel("练习题信息")
public class ExercisesInfoVo extends BaseVo {

    @ApiModelProperty(value = "练习题ID")
    private Long id;

    @ApiModelProperty(value = "练习题题目")
    private String title;

    @ApiModelProperty(value = "题目音频文件地址")
    private String audioUrl;

    @ApiModelProperty(value = "答案（练习题选项ID）")
    private Long answer;

    List<ExercisesOptionsVo> exercisesOptionsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public Long getAnswer() {
        return answer;
    }

    public void setAnswer(Long answer) {
        this.answer = answer;
    }

    public List<ExercisesOptionsVo> getExercisesOptionsList() {
        return exercisesOptionsList;
    }

    public void setExercisesOptionsList(List<ExercisesOptionsVo> exercisesOptionsList) {
        this.exercisesOptionsList = exercisesOptionsList;
    }

    @Override
    public String toString() {
        return "ExercisesInfoVo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                ", answer='" + answer + '\'' +
                ", exercisesOptionsList=" + exercisesOptionsList +
                '}';
    }
}
