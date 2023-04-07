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

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 积分详情
 * </p>
 *
 * @author 木心
 * @since 2022-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("points_details")
@ApiModel(value="PointsDetails对象", description="积分详情")
@Accessors(chain = true)
public class PointsDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "事件信息")
    @TableField("event")
    private String event;

    @ApiModelProperty(value = "成就点减少或增加的描述")
    @TableField("change_amount")
    private String changeAmount;

    @ApiModelProperty(value = "事件发生的时间")
    @TableField("occurring_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime occurringTime;


}
