package com.ljh.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.Comment;
import com.ljh.project.entity.param.CommentParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.mapper.CommentMapper;
import com.ljh.project.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


public class ICommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    ICommentServiceImpl iCommentService;

    @Override
    public List<Comment> getCommentList(CommentParam commentParam) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("subject_id", commentParam.getSubjectId());
        if(commentParam.getType() == 1 ){
            queryWrapper.orderByDesc("likes").last("limit"+(commentParam.getNum()-1)*commentParam.getCommentSize()+","+commentParam.getCommentSize());
        }else{
            queryWrapper.orderByDesc("time").last("limit"+(commentParam.getNum()-1)*commentParam.getCommentSize()+","+commentParam.getCommentSize());
        }
        List<Comment> list = iCommentService.list(queryWrapper);

        return list;
    }
}
