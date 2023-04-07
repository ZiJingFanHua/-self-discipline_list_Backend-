package com.ljh.project.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:hxy
 * @date 2022/1/18
 * @apiNote
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardParam {

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "奖励描述")
    private String description;

    @ApiModelProperty(value = "耗费成就点数")
    private Integer exchangePoints;

}
