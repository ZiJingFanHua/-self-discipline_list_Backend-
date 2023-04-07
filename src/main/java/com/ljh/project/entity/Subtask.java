package com.ljh.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 子任务表
 * </p>
 *
 * @author 木心
 * @since 2022-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("subtask")
@ApiModel(value="Subtask对象", description="子任务表")
public class Subtask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "无需返回，父任务id")
    @TableField("father_id")
    private Long fatherId;

    @ApiModelProperty(value = "无需返回，状态(0为未完成，1是完成)")
    @TableField("status")
    private Integer status = 0;

    @ApiModelProperty(value = "子任务描述")
    @TableField("description")
    private String description;


}
