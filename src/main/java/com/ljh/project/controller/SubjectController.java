package com.ljh.project.controller;

import com.ljh.project.entity.Subject;
import com.ljh.project.entity.param.SubjectParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.vo.SubjectVo;
import com.ljh.project.service.impl.SubjectServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    SubjectServiceImpl subjectService;

    @GetMapping("/getSubjectList")
    @ApiOperation(value = "获取话题列表")
    public BaseResult getSubjectList(Integer type,String id){
        List<SubjectVo> subjectListByType = subjectService.getSubjectListByType(type,id);
        return new OkResult("获取成功",subjectListByType);
    }
    @GetMapping("/getSubject")
    @ApiOperation(value = "获取话题详情")
    public BaseResult getSubject(long SubjectId,String id){
        SubjectVo subject = subjectService.getSubject(SubjectId,id);
        return new OkResult("获取成功",subject);
    }

    @PostMapping("/addSubject")
    @ApiOperation(value = "添加话题")
    public BaseResult addSubject(@RequestBody SubjectParam subjectParam){
        subjectService.addSubject(subjectParam);
        return new OkResult("添加成功",null);
    }

    @GetMapping("/getSubjectListById")
    @ApiOperation(value = "获取话题列表")
    public BaseResult getSubjectList(String id){
        List<SubjectVo> subjectListByType = subjectService.getSubjectListByTd(id);
        return new OkResult("获取成功",subjectListByType);
    }


    @GetMapping("/getSubjectListByLike")
    @ApiOperation(value = "获取话题列表")
    public BaseResult getSubjectListByLike(String id){
        List<SubjectVo> subjectListByType = subjectService.getSubjectListByLike(id);
        return new OkResult("获取成功",subjectListByType);
    }

    @GetMapping("/getSubjectListByContent")
    @ApiOperation(value = "获取话题列表")
    public BaseResult getSubjectListByLike(String id,String content){
        List<SubjectVo> subjectListByContent = subjectService.getSubjectListByContent(id,content);
        return new OkResult("获取成功",subjectListByContent);
    }

}
