package com.ljh.project.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 *Author zijing
 *Date 2023/4/18 16:17
 * 获取评论参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentParam {

    /**
     * 查询评论类型
     * 1：热门
     * 2：最新
     */
    @ApiModelProperty(value = "查询类型(默认为热门)")
    private Integer type = 1;

    @ApiModelProperty(value = "查看人")
    private String id;

    /**
     * 查询评论的话题
     */
    @ApiModelProperty(value = "查询评论的话题id")
    private Long subjectId;

    /**
     * 每次查询评论数量
     */
    @ApiModelProperty(value = "分页规格(默认为10)")
    private Integer commentSize = 10;

    /**
     * 查询次数
     */
    @ApiModelProperty(value = "第几次查询")
    private Integer num = 1;
}
