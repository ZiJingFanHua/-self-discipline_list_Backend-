package com.ljh.project.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DaysListVo {
    @ApiModelProperty(value = "月份")
    private Integer[] month;

    @ApiModelProperty(value = "坚持天数")
    private Integer[] days;

}
