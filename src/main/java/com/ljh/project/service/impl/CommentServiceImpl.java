package com.ljh.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.Comment;
import com.ljh.project.entity.CommentRelationship;
import com.ljh.project.entity.Like;
import com.ljh.project.entity.Userinfo;
import com.ljh.project.entity.param.CommentAddParam;
import com.ljh.project.entity.param.CommentParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.vo.CommentVo;
import com.ljh.project.mapper.CommentMapper;
import com.ljh.project.mapper.CommentRelationshipMapper;
import com.ljh.project.mapper.SubjectMapper;
import com.ljh.project.mapper.UserinfoMapper;
import com.ljh.project.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    CommentServiceImpl iCommentService;
    @Autowired
    CommentRelationshipMapper commentRelationshipMapper;

    @Autowired
    CommentRelationshipServiceImpl commentRelationshipService;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    UserinfoMapper userinfoMapper;

    @Autowired
    LikeServiceImpl likeService;
    @Override
    public List<CommentVo> getCommentList(CommentParam commentParam) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("subject_id", commentParam.getSubjectId());
        queryWrapper.eq("form_id","");
        if(commentParam.getType() == 2 ){
            //热门评论
            queryWrapper.orderByDesc("likes").last("limit "+(commentParam.getNum()-1)*commentParam.getCommentSize()+","+commentParam.getCommentSize());
        }else{
            //最新评论
            queryWrapper.orderByDesc("time").last("limit "+(commentParam.getNum()-1)*commentParam.getCommentSize()+","+commentParam.getCommentSize());
        }
        List<CommentVo> commentVos = new ArrayList();
        List<Comment> list = iCommentService.list(queryWrapper);
        for(int i = 0;i<list.size();i++){
            Like one = null;
            if(commentParam.getId()!=null) {
                QueryWrapper<Like> likeQueryWrapper = new QueryWrapper<>();
                likeQueryWrapper.eq("account_id", commentParam.getId());
                likeQueryWrapper.eq("comment_id", list.get(i).getId());
                one = likeService.getOne(likeQueryWrapper);
            }
                Comment comment = list.get(i);
                String commenterId = comment.getCommenterId();
                Userinfo userInfoById = userinfoMapper.getUserInfoById(commenterId);
                CommentVo commentVo = new CommentVo(comment, userInfoById);
                if (one != null)
                    commentVo.setIslike(1);

            commentVos.add(commentVo);
            List<CommentVo> childrenCommentList = getChildrenCommentList(comment,commentParam.getId());
            commentVo.setSubjectVoList(childrenCommentList);
        }


//        return list;
        return commentVos;
    }

    /***
     * 寻找子评论
     * @param comment
     * @return
     */
    public List<CommentVo> getChildrenCommentList(Comment comment,String userId){

            List<CommentVo> commentList = new ArrayList<>();
            Long id = comment.getId();
            String commenterId1 = comment.getCommenterId();
        Userinfo userInfoById1 = userinfoMapper.getUserInfoById(commenterId1);
        QueryWrapper<CommentRelationship> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id", id);
            List<CommentRelationship> lists = commentRelationshipService.list(queryWrapper);
            if(lists.size() == 0){
                return commentList;
            }
            //寻找子评论
            for(int j = 0;j<lists.size();j++){
                Like one = null;
                Comment byId = commentService.getById(lists.get(j).getChildId());
                String commenterId = byId.getCommenterId();
                Userinfo userInfoById = userinfoMapper.getUserInfoById(commenterId);

                if(userId!=null) {
                    QueryWrapper<Like> likeQueryWrapper = new QueryWrapper<>();
                    likeQueryWrapper.eq("account_id", userId);
                    likeQueryWrapper.eq("comment_id", byId.getId());
                    one = likeService.getOne(likeQueryWrapper);
                }


                CommentVo commentVo = new CommentVo(byId, userInfoById);
                if(one!=null)
                    commentVo.setIslike(1);
                commentVo.setBeNickName(userInfoById1.getNickname());
                commentList.add(commentVo);
                commentList.addAll(getChildrenCommentList(byId,userId));
            }
           return commentList;




//        List<Comment> commentList = new ArrayList<>();
//        for (int i = 0 ;i<list.size();i++){
//            Comment comment = list.get(i);
//            List<Comment> newCommentList = new ArrayList<>();
//            String commenterId = comment.getCommenterId();
//            QueryWrapper<CommentRelationship> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("parent_id", commenterId);
//            List<CommentRelationship> lists = commentRelationshipService.list(queryWrapper);
//            if(lists.size() == 0){
//                return commentList;
//            }
//            //寻找子评论
//            for(int j = 0;j<lists.size();j++){
//                Comment byId = commentService.getById(lists.get(j).getChildId());
//                newCommentList.add(byId);
//            }
//            commentList.addAll(newCommentList);
//            List<Comment> childrenCommentList = getChildrenCommentList(newCommentList);
//            commentList.addAll(childrenCommentList);
//        }
//        return commentList;
    }

    @Override
    public BaseResult getComments(CommentParam commentParam) {
        List<CommentVo> commentList = iCommentService.getCommentList(commentParam);
        OkResult okResult = new OkResult("查询成功",commentList);
        return  okResult;
    }

    @Override
    public Boolean addComment(CommentAddParam commentAddParam) {
        Long subjectId = commentAddParam.getSubjectId();
        subjectMapper.updateCommentsNumLong(subjectId);
        Comment comment = commentAddParam.getComment();
//        commentMapper.insert(comment);
        commentMapper.insertComment(comment);
        if(!commentAddParam.getFormId().equals("")){
            CommentRelationship commentRelationship = new CommentRelationship();
            commentRelationship.setParentId(commentAddParam.getFormCommentId());
            commentRelationship.setChildId(comment.getId());
            commentRelationshipMapper.insert(commentRelationship);
        }
        return true;
    }

    /***
     * 获取回复列表
     * @param id
     * @return
     */
    @Override
    public List<CommentVo> getReplyList(String id) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("form_id",id);
        List<CommentVo> commentVos = new ArrayList();
        List<Comment> list = iCommentService.list(queryWrapper);
        for(int i = 0;i<list.size();i++){
            Userinfo userInfoById = userinfoMapper.getUserInfoById(list.get(i).getCommenterId());
            commentVos.add(new CommentVo(list.get(i),userInfoById));
        }
        return commentVos;
    }

    @Override
    public CommentVo getReply(String id, Long commentid) {
        List<CommentVo> commentVos = new ArrayList();
        CommentVo commentVoById = getCommentVoById(id, commentid);
        commentMapper.updataLook(commentid);
        commentVos.add(commentVoById);
        QueryWrapper<CommentRelationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("child_id",commentid);
        CommentRelationship one = commentRelationshipService.getOne(queryWrapper);
        while(one !=null){
            CommentVo commentVoById1 = getCommentVoById(id, one.getParentId());
            commentVos.add(commentVoById1);
            QueryWrapper<CommentRelationship> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("child_id",one.getParentId());
            one = commentRelationshipService.getOne(queryWrapper1);
        }
        CommentVo commentVo = commentVos.get(commentVos.size() - 1);
        commentVos.remove(commentVos.size() - 1);
        commentVo.setSubjectVoList(commentVos);
        return commentVo;
    }


    /**
     * 通过查询用户与评论id得到返回评论
     * @param id
     * @param commentid
     * @return
     */
    public CommentVo getCommentVoById(String id, Long commentid){
        Comment commentById = commentMapper.getCommentById(commentid);
        Userinfo userInfoById = userinfoMapper.getUserInfoById(commentById.getCommenterId());

        CommentVo commentVo = new CommentVo(commentById,userInfoById);
        if(commentById.getFormId()!=null&&!commentById.getFormId().equals("")) {
            Userinfo userInfoById1 = userinfoMapper.getUserInfoById(commentById.getFormId());
            commentVo.setBeNickName(userInfoById1.getNickname());
        }
        QueryWrapper<Like> likeQueryWrapper = new QueryWrapper<>();
        likeQueryWrapper.eq("account_id",id);
        likeQueryWrapper.eq("comment_id",commentById.getId());
        Like one = likeService.getOne(likeQueryWrapper);
        if(one != null)
            commentVo.setIslike(1);
        return commentVo;
    }

}

