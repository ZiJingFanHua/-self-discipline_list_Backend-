package com.ljh.project.entity.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ljh.project.entity.Comment;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/***
 *Author zijing
 *Date 2023/5/7 16:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddParam {
    @ApiModelProperty(value = "评论id")
    private Long id;

    @ApiModelProperty(value = "评论者id")
    private String commenterId;

    @ApiModelProperty(value = "话题id")
    private Long subjectId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "点赞数")
    private Integer likes = 0;

    @ApiModelProperty(value = "被评论Id")
    private Long formCommentId;

    @ApiModelProperty(value = "被评论用户id")
    private String formId = "";

    @ApiModelProperty(value = "创建时间")
    private LocalDate time = LocalDate.now();

    @ApiModelProperty(value = "是否被查看")
    private Integer look = 0;

    public Comment getComment(){
        Comment comment = new Comment();
        comment.setContent(this.content);
        comment.setCommenterId(this.commenterId);
        comment.setFormId(this.formId);
        comment.setTime(this.time);
        comment.setLikes(this.likes);
        comment.setLook(this.look);
        comment.setSubjectId(this.subjectId);
        comment.setId(this.id);
        return comment;
    }
}
