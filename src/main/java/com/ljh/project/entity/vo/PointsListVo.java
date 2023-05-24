package com.ljh.project.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 *Author zijing
 *Date 2023/5/18 14:15
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointsListVo {
    private Integer[] month;
    private Integer[] income;
    private Integer[] expenditure;
}
