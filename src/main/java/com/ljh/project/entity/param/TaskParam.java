package com.ljh.project.entity.param;

import com.ljh.project.entity.Subtask;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author:hxy
 * @date 2022/4/21
 * @apiNote
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskParam {

    @ApiModelProperty("任务id")
    private Long id;

    @ApiModelProperty("任务标题")
    private String title;

    @ApiModelProperty("任务描述")
    private String description;

    @ApiModelProperty("截止时间")
    private LocalDate deadTime;

    @ApiModelProperty("奖励点数")
    private int rewardPoints;

    @ApiModelProperty("子任务列表")
    private List<Subtask> subtasks;


}
