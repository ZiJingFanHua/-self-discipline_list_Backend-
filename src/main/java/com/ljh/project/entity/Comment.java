package com.ljh.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 *Author zijing
 *Date 2023/4/18 14:24
 * 评论
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("comment")
@ApiModel(value="comment对象", description="")
public class Comment {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "评论者id")
    @TableField("commenter_id")
    private Long commenterId;

    @ApiModelProperty(value = "话题id")
    @TableField("subject_id")
    private Long subjectId;

    @ApiModelProperty(value = "评论内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "点赞数")
    @TableField("likes")
    private Long likes;
}
