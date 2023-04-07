package com.ljh.project.controller;


import com.ljh.project.entity.param.PointArchiveParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.service.IPointsDetailsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 积分详情 前端控制器
 * </p>
 *
 * @author 木心
 * @since 2022-04-18
 */
@RestController
@RequestMapping("/pointsDetails")
public class PointsDetailsController {

    @Resource
    IPointsDetailsService iPointsDetailsService;


    @PostMapping("/archive")
    @ApiOperation(value = "返回归档月份")
    public BaseResult getArchive(String userId){
        return iPointsDetailsService.getArchive(userId);
    }

    @PostMapping("/getArchiveBydate")
    @ApiOperation(value = "根据选择时间返回指定年月的积分明细")
    public BaseResult getArchiveByDate(@RequestBody PointArchiveParam pointArchiveParam){

        return iPointsDetailsService.getArchiveBydate(pointArchiveParam);
    }

}
