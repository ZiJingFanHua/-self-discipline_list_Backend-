package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.ParentTask;
import com.ljh.project.entity.param.TaskListParam;
import com.ljh.project.entity.result.BaseResult;

/**
 * <p>
 * 父任务表 服务类
 * </p>
 *
 * @author 木心
 * @since 2022-04-21
 */
public interface IParentTaskService extends IService<ParentTask> {

    Long insertParentTask(ParentTask parentTask);

    BaseResult getTaskList(TaskListParam taskListParam);

    ParentTask getParentTaskById(Long id);
}
