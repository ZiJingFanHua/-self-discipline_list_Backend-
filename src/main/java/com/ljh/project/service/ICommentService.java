package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.Comment;
import com.ljh.project.entity.param.CommentAddParam;
import com.ljh.project.entity.param.CommentParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.vo.CommentVo;

import java.util.List;
/***
 *Author zijing
 *Date 2023/4/19 9:04
 * 评论服务类
 */
public interface ICommentService extends IService<Comment> {

//    根据传入的参数获取热门或者最新的评论列表
    List<CommentVo> getCommentList(CommentParam commentParam);

//    获取评论
    BaseResult getComments(CommentParam commentParam);

    Boolean addComment(CommentAddParam commentAddParam);

    List<CommentVo> getReplyList(String id);

    CommentVo getReply(String id,Long commentid);
}
