package com.ljh.project.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeParam {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "")
    private String accountId;

    /**
     * 话题id
     */
    @ApiModelProperty(value = "")
    private Long subjectId;
    /**
     * 评论id
     */
    @ApiModelProperty(value = "")
    private Long commentId;
}
