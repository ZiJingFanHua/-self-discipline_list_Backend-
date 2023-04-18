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
 *Date 2023/4/18 14:53
 * 评论关系
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("comment关系")
@ApiModel(value="comment关系对象", description="")
public class CommentRelationship {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父评论id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "子评论id")
    @TableField("child_id")
    private Long childId;

}
