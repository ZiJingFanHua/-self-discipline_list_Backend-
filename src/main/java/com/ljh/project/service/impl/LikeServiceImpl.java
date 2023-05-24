package com.ljh.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljh.project.entity.Comment;
import com.ljh.project.entity.Like;
import com.ljh.project.mapper.CommentMapper;
import com.ljh.project.mapper.LikeMapper;
import com.ljh.project.mapper.SubjectMapper;
import com.ljh.project.service.ILikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zijing
 * @since 2023-05-10
 */

@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements ILikeService {


    @Autowired
    LikeMapper likeMapper;

    @Autowired
    LikeServiceImpl likeService;
    @Autowired
    SubjectMapper subjectMapper;
    @Autowired
    CommentMapper commentMapper;
    /**
     * 添加点赞
     * @param like
     * @return
     */
    @Override
    public boolean addLike(Like like) {
        likeMapper.insert(like);
        if(like.getCommentId() == null){
         subjectMapper.addLikes(like.getSubjectId());
        }else{
            commentMapper.addLikes(like.getCommentId());
        }
        return true;
    }

    /**
     * 删除点赞
     * @param like
     * @return
     */
    @Override
    public boolean droplike(Like like) {
        QueryWrapper<Like> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id",like.getAccountId());
        if(like.getCommentId() == null)
            queryWrapper.eq("subject_id",like.getSubjectId());
        else
            queryWrapper.eq("comment_id",like.getCommentId());
        Like one = likeService.getOne(queryWrapper);
        if(one!=null){
            likeMapper.deleteById(one.getId());
            if(like.getCommentId() == null)
                subjectMapper.deleteLikes(like.getSubjectId());
            else
                commentMapper.deleteLikes(like.getCommentId());
        }
        return true;
    }
}
