package com.ljh.project.controller;

import com.ljh.project.entity.Comment;
import com.ljh.project.entity.param.CommentAddParam;
import com.ljh.project.entity.param.CommentParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.vo.CommentVo;
import com.ljh.project.service.impl.CommentServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/***
 *Author zijing
 *Date 2023/4/18 16:07
 * 评论控制器
 */
@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    CommentServiceImpl iCommentService;
    @GetMapping("/getCommentList")
    @ApiOperation(value = "获取评论列表")
    public BaseResult getSubjectList( CommentParam commentParam){
        List<CommentVo> list = iCommentService.getCommentList(commentParam);
        return new OkResult("查询成功",list);
    }

    @PostMapping("/addComment")
    @ApiOperation(value = "添加评论")
    public BaseResult addComment(@RequestBody CommentAddParam commentAddParam){
//        BaseResult comments = iCommentService.getComments(commentParam);
//        return comments;
        Boolean aBoolean = iCommentService.addComment(commentAddParam);
        return new OkResult("添加成功",null);
    }

    @GetMapping("getReply")
    @ApiOperation(value = "获取回复列表")
    public BaseResult getReplyList(String id){
        List<CommentVo> list = iCommentService.getReplyList(id);
        return new OkResult("查询成功",list);
    }

    @GetMapping("getReplyById")
    @ApiOperation(value = "获取回复列表")
    public BaseResult getReply(String id,Long commentId){
        CommentVo reply = iCommentService.getReply(id, commentId);
        List<CommentVo> commentVos = new ArrayList<>();
        commentVos.add(reply);
        return new OkResult("查询成功",commentVos);
    }


}
