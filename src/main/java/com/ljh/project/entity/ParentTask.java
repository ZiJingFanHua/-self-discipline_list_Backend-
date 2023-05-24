package com.ljh.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/***
 *Author zijing
 *Date 2023/5/18 11:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("parent_task")
@ApiModel(value = "ParentTask对象", description = "父任务表")
@Accessors(chain = true)
public class ParentTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "任务标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "任务描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "截止时间,格式 yyyy-MM-dd 如 2022-01-20")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("dead_time")
    private LocalDate deadTime;

    @ApiModelProperty(value = "奖励点数")
    @TableField("reward_points")
    private Integer rewardPoints;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    @NotNull
    private String userId;


    @ApiModelProperty(value = "无需返回，任务状态（0，代表未完成，1，代表完成）")
    @TableField("status")
    private Integer status = 0;

    @ApiModelProperty(value = "无需返回，子任务未完成数量")
    @TableField("unfinished_number")
    private Integer unfinishedNumber;

    @ApiModelProperty(value = "子任务列表")
    @TableField(exist = false)
    private List<Subtask> subtaskList;

    @ApiModelProperty(value = "完成时间")
    @TableField("fininshed_time")
    private LocalDate finishedTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDate createTime = LocalDate.now();
}
