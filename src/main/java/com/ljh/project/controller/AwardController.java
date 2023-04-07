package com.ljh.project.controller;


import com.ljh.project.entity.param.AwardExchangeParam;
import com.ljh.project.entity.param.AwardParam;
import com.ljh.project.entity.param.AwardSearchParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.service.IAwardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 木心
 * @since 2022-01-16
 */
@RestController
@RequestMapping("/award")
public class AwardController {

    @Autowired
    IAwardService iAwardService;

    @PostMapping("/getawardlist")
    @ApiOperation(value = "查询奖励列表")
    public BaseResult getAwardList(String userId){
        return iAwardService.getAwardList(userId);
    }

    @ApiOperation(value = "兑换奖励")
    @PostMapping("/exchangeaward")
    public BaseResult exchangeAward(@RequestBody AwardExchangeParam awardExchangeParam){

        System.out.println(awardExchangeParam.getAwardId());
        return iAwardService.exchangeAward(awardExchangeParam);
    }

    @ApiOperation(value = "设置奖励")
    @PostMapping("/setaward")
    public BaseResult setAward(@RequestBody AwardParam awardParam){
        return iAwardService.setAward(awardParam);

    }

    @ApiOperation(value = "搜索奖励")
    @PostMapping("/searchAward")
    public BaseResult searchAward(@RequestBody AwardSearchParam awardSearchParam){
        return iAwardService.searchAward(awardSearchParam);
    }


}
