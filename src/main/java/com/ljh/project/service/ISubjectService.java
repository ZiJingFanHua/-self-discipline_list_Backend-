package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.Subject;
import com.ljh.project.entity.result.BaseResult;

/***
 *Author zijing
 *Date 2023/4/18 15:58
 * 话题服务类
 */
public interface ISubjectService extends IService<Subject> {

    BaseResult getSubject(Long subjectId);
}
