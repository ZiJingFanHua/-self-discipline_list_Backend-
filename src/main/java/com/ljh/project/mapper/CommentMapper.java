package com.ljh.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.project.entity.Comment;

/***
 *Author zijing
 *Date 2023/4/18 15:28
 */
public interface CommentMapper extends BaseMapper<Comment> {
    public long insertComment(Comment comment);

    public Long addLikes(Long id);

    public Long deleteLikes(Long id);

    public Comment getCommentById(Long id);

    public Long updataLook(Long id);
}
