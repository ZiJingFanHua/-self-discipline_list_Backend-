package com.ljh.project.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:hxy
 * @date 2022/4/24
 * @apiNote
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardExchangeParam {

    @ApiModelProperty("奖励id")
    private Long awardId;

    @ApiModelProperty("用户id")
    private String userId;

}
