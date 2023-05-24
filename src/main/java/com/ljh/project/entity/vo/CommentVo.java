package com.ljh.project.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ljh.project.entity.Comment;
import com.ljh.project.entity.Userinfo;
import com.ljh.project.entity.param.CommentAddParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/***
 *Author zijing
 *Date 2023/5/8 12:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {

    private Long id;


    @ApiModelProperty(value = "评论者名称")
    private String nickName;

    @ApiModelProperty(value = "评论者头像")
    private String headPhoto;

    @ApiModelProperty(value = "评论者打卡天数")
    private Integer days;

    @ApiModelProperty(value = "评论者id")
    private String commenterId;

    @ApiModelProperty(value = "话题id")
    private Long subjectId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "点赞数")
    private Integer likes = 0;

    @ApiModelProperty(value = "被评论用户id")
    private String formId;

    @ApiModelProperty(value = "被评论用户名字")
    private String BeNickName;

    @ApiModelProperty(value = "创建时间")
    private LocalDate time = LocalDate.now();

    @ApiModelProperty(value = "是否被查看")
    private Integer look = 0;
    @ApiModelProperty(value = "是否被点赞")
    private Integer islike = 0;
    @ApiModelProperty(value = "子评论")
    private List<CommentVo> subjectVoList;

    public CommentVo(Comment comment, Userinfo userinfo){
        this.nickName = userinfo.getNickname();
        this.commenterId = comment.getCommenterId();
        this.content = comment.getContent();
        this.headPhoto = userinfo.getHeadPhoto();
        this.days = userinfo.getDays();
        this.subjectId = comment.getSubjectId();
        this.formId = comment.getFormId();
        this.look = comment.getLook();
        this.time = comment.getTime();
        this.id = comment.getId();
        this.likes = comment.getLikes();
    }

}
