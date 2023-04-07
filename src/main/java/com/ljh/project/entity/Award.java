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
@TableName("award")
@ApiModel(value="Award对象", description="")
public class Award implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "奖励描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "奖励兑换需要的成就点数")
    @TableField("exchange_points")
    private Integer exchangePoints;

    @ApiModelProperty(value = "奖励对应的任务id")
    @TableField("task_id")
    private Long taskId;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;


}
