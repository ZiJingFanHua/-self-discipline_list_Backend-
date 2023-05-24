package com.ljh.project.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectParam {

    @ApiModelProperty("发布者id")
    private String publishId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("点赞数")
    private Integer likes = 0;

    @ApiModelProperty("评论数")
    private Integer commentsNum = 0;

    @ApiModelProperty("创建时间")
    private LocalDate time = LocalDate.now();
}
