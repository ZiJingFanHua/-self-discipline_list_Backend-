package com.ljh.project.entity.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author:hxy
 * @date 2022/4/21
 * @apiNote
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListParam {

    @ApiModelProperty("当前用户的id")
    private String userId;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("查询的时间")
    private LocalDate deadTime;

}
