package com.ljh.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.Like;
import com.ljh.project.entity.Subject;
import com.ljh.project.entity.Userinfo;
import com.ljh.project.entity.param.SubjectParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.vo.SubjectVo;
import com.ljh.project.mapper.SubjectMapper;
import com.ljh.project.mapper.UserinfoMapper;
import com.ljh.project.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/***
 *Author zijing
 *Date 2023/4/18 16:03
 * 话题服务实现类
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {

    @Autowired
    CommentServiceImpl iCommentService;

    @Autowired
    SubjectServiceImpl iSubjectService;

    @Resource
    SubjectMapper subjectMapper;

    @Resource
    UserinfoMapper userinfoMapper;

    @Autowired
    LikeServiceImpl likeService;
    @Override
    public SubjectVo getSubject(Long subjectId,String id) {

        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",subjectId);
        Subject one = iSubjectService.getOne(queryWrapper);
        //判断是否存在该话题
        if(one == null){
           return null;
        }
        QueryWrapper<Like> likeQueryWrapper = new QueryWrapper<>();
        likeQueryWrapper.eq("account_id",id);
        likeQueryWrapper.eq("subject_id",subjectId);
        Like one1 = likeService.getOne(likeQueryWrapper);

        Userinfo userInfoById = userinfoMapper.getUserInfoById(one.getPublisherId());
        SubjectVo subjectVo = new SubjectVo(one, userInfoById);
        if(one1!=null)
            subjectVo.setIsLike(1);
        return subjectVo;
    }

    /**
     * 添加话题
     * @param subjectParam
     * @return
     */
    @Override
    public Boolean addSubject(SubjectParam subjectParam) {
        Subject subject = new Subject();
        subject.setContent(subjectParam.getContent());
        subject.setCommentsNum(subjectParam.getCommentsNum());
        subject.setLikes(subjectParam.getLikes());
        subject.setPublisherId(subjectParam.getPublishId());
        subject.setTime(subjectParam.getTime());
        subjectMapper.insertSubject(subject);
        return true;
    }

    /**
     * 获取话题列表
     * 1:热度
     * 2:时间
     * @param type
     * @return
     */
    @Override
    public List<SubjectVo> getSubjectListByType(Integer type,String id) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        if(type == 1 )
        queryWrapper.orderByDesc("comments_num");
        else
            queryWrapper.orderByDesc("time");
        List<Subject> list = iSubjectService.list(queryWrapper);
        String publisherId = "";
        List<SubjectVo> subjectVos = new ArrayList<>();
        for(int i = 0 ;i<list.size();i++){
            QueryWrapper<Like> likeQueryWrapper = new QueryWrapper<>();
            likeQueryWrapper.eq("account_id",id);
            likeQueryWrapper.eq("subject_id",list.get(i).getId());
            Like one = likeService.getOne(likeQueryWrapper);
            publisherId = list.get(i).getPublisherId();
            Userinfo userInfoById = userinfoMapper.getUserInfoById(publisherId);
            SubjectVo subjectVo = new SubjectVo(list.get(i),userInfoById);
            if(one != null)
                subjectVo.setIsLike(1);
            subjectVos.add(subjectVo);
        }
        return subjectVos;
    }

    /***
     * 通过用户id获取话题
     * @param id
     * @return
     */
    @Override
    public List<SubjectVo> getSubjectListByTd(String id) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("publisher_id",id);
        List<Subject> list = iSubjectService.list(queryWrapper);
        Userinfo userInfoById = userinfoMapper.getUserInfoById(id);
        List<SubjectVo> subjectVos = new ArrayList<>();
        for(int i = 0;i<list.size();i++) {
            Like one = null;
            if(id !=null) {
                QueryWrapper<Like> likeQueryWrapper = new QueryWrapper<>();
                likeQueryWrapper.eq("account_id", id);
                likeQueryWrapper.eq("subject_id", list.get(i).getId());
                 one = likeService.getOne(likeQueryWrapper);
            }
            SubjectVo subjectVo = new SubjectVo(list.get(i), userInfoById);
            if(one != null)
                subjectVo.setIsLike(1);
            subjectVos.add(subjectVo);
        }
        return subjectVos;
    }

    @Override
    public List<SubjectVo> getSubjectListByLike(String id) {
        List<SubjectVo> subjectVos = new ArrayList<>();
        QueryWrapper<Like> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id",id);
        queryWrapper.isNotNull("subject_id");
        List<Like> list1 = likeService.list(queryWrapper);
        for(int i = 0;i<list1.size();i++){
            Subject subject = subjectMapper.selectById(list1.get(i).getSubjectId());
            Userinfo userInfoById = userinfoMapper.getUserInfoById(subject.getPublisherId());
            SubjectVo subjectVo = new SubjectVo(subject, userInfoById);
            subjectVo.setIsLike(1);
            subjectVos.add(subjectVo);
        }
        return subjectVos;
    }

    /**
     * 模糊查询
     * @param id
     * @param content
     * @return
     */
    @Override
    public List<SubjectVo> getSubjectListByContent(String id, String content) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("content",'%'+content+"%");
        List<Subject> list = iSubjectService.list(queryWrapper);
        List<SubjectVo> subjectVos = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            QueryWrapper<Like> likeQueryWrapper = new QueryWrapper<>();
            likeQueryWrapper.eq("account_id",id);
            likeQueryWrapper.eq("subject_id",list.get(i).getId());
            Like one = likeService.getOne(likeQueryWrapper);
            Userinfo userInfoById = userinfoMapper.getUserInfoById(list.get(i).getPublisherId());
            SubjectVo subjectVo = new SubjectVo(list.get(i), userInfoById);
            if(one!=null)
            subjectVo.setIsLike(1);
            subjectVos.add(subjectVo);
        }
        return subjectVos;
    }
}
