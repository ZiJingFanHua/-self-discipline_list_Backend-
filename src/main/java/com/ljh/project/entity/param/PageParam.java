package com.ljh.project.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

/**
 * @author muxin
 * @date
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {

    /**
     * 每页的大小
     */
    @ApiModelProperty(value = "分页规格(默认为10)")
    private Integer pageSize = 10;

    /**
     *分页第几页
     */
    @Max(200)
    @ApiModelProperty(value = "分页页码(默认为1)")
    private Integer pageNum = 1;
}
