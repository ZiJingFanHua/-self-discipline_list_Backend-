package com.ljh.project.entity.vo;

import com.ljh.project.entity.BigTask;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountMessageVo {
    @ApiModelProperty("打卡天数")
    private int days;

    @ApiModelProperty("总点数")
    private Long total;

    @ApiModelProperty("发表话题")
    private int talkNums;

    @ApiModelProperty("评论数")
    private int comments;

    @ApiModelProperty("喜欢")
    private int likes;

    @ApiModelProperty("待完成的任务")
    private BigTask task;
}
