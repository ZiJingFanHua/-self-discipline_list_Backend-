package com.ljh.project.entity.vo;

import com.ljh.project.entity.PointsDetails;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author:hxy
 * @date 2022/4/23
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointsDetailVo {

    @ApiModelProperty("获得的明细表")
    List<PointsDetails> pointsDetails;

    @ApiModelProperty("当月总收入")
    private int TotalRevenue;

    @ApiModelProperty("当月总支出")
    private int TotalSpending;

}
