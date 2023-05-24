package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.Subject;
import com.ljh.project.entity.param.SubjectParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.vo.SubjectVo;

import java.util.List;

/***
 *Author zijing
 *Date 2023/4/18 15:58
 * 话题服务类
 */
public interface ISubjectService extends IService<Subject> {

    public SubjectVo getSubject(Long subjectId,String id);

    public Boolean addSubject(SubjectParam subjectParam);

    public List<SubjectVo> getSubjectListByType(Integer type,String id);

    public List<SubjectVo> getSubjectListByTd(String id);


    public List<SubjectVo> getSubjectListByLike(String id);

    public List<SubjectVo> getSubjectListByContent(String id,String content);
}
