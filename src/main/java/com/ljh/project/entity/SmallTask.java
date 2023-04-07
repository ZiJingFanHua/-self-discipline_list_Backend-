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
 * 
 * </p>
 *
 * @author 木心
 * @since 2022-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("small_task")
@ApiModel(value="SmallTask对象", description="")
public class SmallTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "奖励点数")
    @TableField("reward_points")
    private Integer rewardPoints;

    @ApiModelProperty(value = "1是每日，2是周")
    @TableField("time_cycle")
    private Integer timeCycle;

    @ApiModelProperty(value = "0是重要，1是不重要")
    @TableField("is_importance")
    private Integer isImportance = 1;

    @ApiModelProperty(value = "0是未完成，1是完成")
    @TableField("is_accomplish")
    private Integer isAccomplish = 0;

    @ApiModelProperty(value = "任务细节描述")
    @TableField("detail")
    private String detail;

    @ApiModelProperty(value = "0是不提醒，1是提醒")
    @TableField("is_remind")
    private Integer isRemind = 0;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;


}
