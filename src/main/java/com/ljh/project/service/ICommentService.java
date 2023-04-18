package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.Comment;
import com.ljh.project.entity.param.CommentParam;
import com.ljh.project.entity.result.BaseResult;

import java.util.List;

public interface ICommentService extends IService<Comment> {

    List<Comment> getCommentList(CommentParam commentParam);
}
