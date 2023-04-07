package com.ljh.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 打卡日历记录
 * </p>
 *
 * @author 木心
 * @since 2022-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("punch_calendar")
@ApiModel(value="PunchCalendar对象", description="打卡日历记录")
@Accessors(chain = true)
public class PunchCalendar implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "年月（2001-1）")
    @TableField("year_mouth")
    private String yearMouth;

    @ApiModelProperty(value = "日历坚持标记")
    @TableField("calendar")
    private String calendar;


}
