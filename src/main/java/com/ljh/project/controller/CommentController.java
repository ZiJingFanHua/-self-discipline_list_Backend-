package com.ljh.project.controller;

import com.ljh.project.entity.param.CommentParam;
import com.ljh.project.entity.result.BaseResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *Author zijing
 *Date 2023/4/18 16:07
 * 评论控制器
 */
@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {

    @PostMapping("/getHotCommentList")
    @ApiOperation(value = "获取评论列表")
    public BaseResult getSubjectList(CommentParam commentParam){

        return null;
    }
}
