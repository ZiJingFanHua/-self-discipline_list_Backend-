package com.ljh.project.entity;

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
@TableName("userinfo")
@ApiModel(value="Userinfo对象", description="")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户微信唯一标识")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "头像地址")
    @TableField("head_photo")
    private String headPhoto;

    @ApiModelProperty(value = "座右铭")
    @TableField("motto")
    private String motto = "行动是治愈迷茫最好的良药！";

    @ApiModelProperty(value = "成就点")
    @TableField("achieve_points")
    private Long achievePoints = 0L;

    @ApiModelProperty(value = "性别（0是女性，1是男性）")
    @TableField("sex")
    private Integer sex = 1;

    @ApiModelProperty(value = "坚持天数")
    @TableField("days")
    private Integer days = 0;



}
