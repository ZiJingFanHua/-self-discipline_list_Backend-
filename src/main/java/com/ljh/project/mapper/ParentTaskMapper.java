package com.ljh.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.project.entity.ParentTask;
import com.ljh.project.entity.param.TaskListParam;

import java.util.List;

/***
 *Author zijing
 *Date 2023/4/18 15:47
 */
public interface ParentTaskMapper extends BaseMapper<ParentTask> {

    /**
     * 创建父任务
     * @param parentTask
     * @return
     */
    public Long insertParentTask(ParentTask parentTask);

    /**
     * 得到对应用户和时间的父任务列表
     * @param taskListParam
     * @return
     */
    List<ParentTask> getParentTasks(TaskListParam taskListParam);


    List<ParentTask> getCompletedTasks(TaskListParam taskListParam);

    List<ParentTask> getUnCompletedTasks(TaskListParam taskListParam);
}
