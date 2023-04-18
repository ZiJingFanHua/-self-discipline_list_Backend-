package com.ljh.project.controller;

import com.ljh.project.entity.result.BaseResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *Author zijing
 *Date 2023/4/18 15:48
 *
 * 话题控制器
 */
@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {
    @PostMapping("/getHotSubjectList")
    @ApiOperation(value = "获取热门话题列表")
    public BaseResult getHotSubjectList(){

        return null;
    }

    @PostMapping("/getNewSubjectList")
    @ApiOperation(value = "获取最新话题列表")
    public BaseResult getNewSubjectList(){

        return null;
    }

    @PostMapping("/getSubject")
    @ApiOperation(value = "获取话题详情")
    public BaseResult getSubject(long SubjectId){

        return null;
    }
}
