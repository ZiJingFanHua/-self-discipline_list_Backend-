package com.ljh.project.entity.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author:hxy
 * @date 2022/4/23
 * @apiNote
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointDetailsParam {

    @ApiModelProperty("事件描述")
    private String event;

    @ApiModelProperty("获得积分情况")
    private String changeAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("事件发生时间")
    private Date occurringTime;

    @ApiModelProperty("当月总收入")
    private int TotalRevenue;

    @ApiModelProperty("当月总支出")
    private int Total_Spending;


}
