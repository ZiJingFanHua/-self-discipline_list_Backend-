package com.ljh.project.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ljh.project.entity.Subject;
import com.ljh.project.entity.Userinfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/***
 *Author zijing
 *Date 2023/5/7 11:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectVo {

    private Long id;

    @ApiModelProperty(value = "发布者id")
    private String publisherId;

    @ApiModelProperty(value = "发布者名称")
    private String nickName;

    @ApiModelProperty(value = "发布者头像")
    private String headPhoto;

    @ApiModelProperty(value = "发布者打卡天数")
    private Integer days;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "点赞数")
    private Integer likes;

    @ApiModelProperty(value = "评论数")
    private Integer commentsNum;

    @ApiModelProperty(value = "创建时间")
    private LocalDate time;

    @ApiModelProperty(value = "是否点赞")
    private Integer isLike = 0;

    public SubjectVo(Subject subject, Userinfo userinfo){
        this.id = subject.getId();
        this.publisherId = userinfo.getId();
        this.commentsNum = subject.getCommentsNum();
        this.content = subject.getContent();
        this.likes = subject.getLikes();
        this.days = userinfo.getDays();
        this.headPhoto = userinfo.getHeadPhoto();
        this.nickName = userinfo.getNickname();
        this.time = subject.getTime();
    }

}
