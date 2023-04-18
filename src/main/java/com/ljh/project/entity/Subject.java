package com.ljh.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/***
 *Author zijing
 *Date 2023/4/18 14:56
 * 话题
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("话题")
@ApiModel(value="话题", description="")
public class Subject {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "发布者id")
    @TableField("publisher_id")
    private Long publisherId;

    @ApiModelProperty(value = "内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "点赞数")
    @TableField("likes")
    private Long likes;

    @ApiModelProperty(value = "评论数")
    @TableField("comments_num")
    private Long commentsNum;

    @ApiModelProperty(value = "创建时间")
    @TableField("time")
    private Date time;
}
