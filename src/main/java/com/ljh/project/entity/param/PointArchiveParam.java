package com.ljh.project.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:hxy
 * @date 2022/4/22
 * @apiNote
 * 返回积分详情的时间
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointArchiveParam {

    @ApiModelProperty("用户Id")
    private String userId;

    @ApiModelProperty("年份")
    private String year;

    @ApiModelProperty("月份")
    private String month;

    @ApiModelProperty("明细条数")
    private int DetailNumber;

}
