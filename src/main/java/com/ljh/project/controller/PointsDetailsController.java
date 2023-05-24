package com.ljh.project.controller;


import com.ljh.project.entity.param.PointArchiveParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.vo.PointsListVo;
import com.ljh.project.service.IPointsDetailsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 *Author zijing
 *Date 2023/5/6 19:29
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

    @GetMapping("/PointList")
    @ApiOperation(value = "返回积分列表")
    public BaseResult getPointList(String id){

        PointsListVo pointsListVo = iPointsDetailsService.getPointsList(id);
        return new OkResult("查询成功", pointsListVo);
//        return iPointsDetailsService.getPointsList(userId);
    }

}
