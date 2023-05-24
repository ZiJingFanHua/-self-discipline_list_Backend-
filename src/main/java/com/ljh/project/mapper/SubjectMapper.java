package com.ljh.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.project.entity.ParentTask;
import com.ljh.project.entity.Subject;
/***
 *Author zijing
 *Date 2023/4/18 15:57
 */
public interface SubjectMapper extends BaseMapper<Subject> {
    /**
     * 创建话题
     * @param subject
     * @return
     */
    public Long insertSubject(Subject subject);

    public Long updateCommentsNumLong(Long id);

    public Long addLikes(Long id);

    public Long deleteLikes(Long id);
}
