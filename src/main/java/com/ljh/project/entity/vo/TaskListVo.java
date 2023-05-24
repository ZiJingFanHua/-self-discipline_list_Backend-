package com.ljh.project.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 *Author zijing
 *Date 2023/5/19 14:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListVo {
    @ApiModelProperty(value = "月份")
    private Integer[] month;

    @ApiModelProperty(value = "完成任务")
    private Integer[] complete;

    @ApiModelProperty(value = "未完成任务")
    private Integer[] unComplete;
}
