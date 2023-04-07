package com.ljh.project.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:hxy
 * @date 2022/4/25
 * @apiNote
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardSearchParam {

    @ApiModelProperty("奖励描述")
    private String description;

    @ApiModelProperty("用户id")
    private String userId;

}
