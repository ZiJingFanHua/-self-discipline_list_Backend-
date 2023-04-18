package com.ljh.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.ParentTask;
import com.ljh.project.entity.Subject;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.mapper.ParentTaskMapper;
import com.ljh.project.mapper.SubjectMapper;
import com.ljh.project.service.ISubjectService;
import org.springframework.stereotype.Service;
/***
 *Author zijing
 *Date 2023/4/18 16:03
 * 话题服务实现类
 */
@Service
public class ISubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {
    @Override
    public BaseResult getSubject(Long subjectId) {
        return null;
    }
}
