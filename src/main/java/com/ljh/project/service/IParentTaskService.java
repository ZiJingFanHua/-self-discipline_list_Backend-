package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.ParentTask;
import com.ljh.project.entity.param.TaskListParam;
import com.ljh.project.entity.param.TaskParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.vo.TaskListVo;

import java.util.List;

/***
 *Author zijing
 *Date 2023/5/19 14:59
 */
public interface IParentTaskService extends IService<ParentTask> {

    Long insertParentTask(ParentTask parentTask);

    BaseResult getTaskList(TaskListParam taskListParam);

    ParentTask getParentTaskById(Long id);

    List<TaskParam> getCreatedTaskList(TaskListParam taskListParam);

    TaskListVo getTaskListDate(String id);

}
