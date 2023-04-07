package com.ljh.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.ParentTask;
import com.ljh.project.entity.param.TaskListParam;
import com.ljh.project.entity.param.TaskParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.TaskCompletionResult;
import com.ljh.project.mapper.ParentTaskMapper;
import com.ljh.project.mapper.SubtaskMapper;
import com.ljh.project.service.IParentTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 父任务表 服务实现类
 * </p>
 *
 * @author 木心
 * @since 2022-04-21
 */
@Service
public class ParentTaskServiceImpl extends ServiceImpl<ParentTaskMapper, ParentTask> implements IParentTaskService {

    @Resource
    ParentTaskMapper parentTaskMapper;

    @Resource
    SubtaskMapper subtaskMapper;
    @Override
    public Long insertParentTask(ParentTask parentTask) {
        Long taskId = parentTaskMapper.insertParentTask(parentTask);
        return taskId;
    }



    /**
     * 得到指定时间和用户的父任务及其子任务列表
     * @param taskListParam
     * @return
     */
    @Override
    public BaseResult getTaskList(TaskListParam taskListParam){
        //定义完成的任务列表
        List<ParentTask> completedParentTask = parentTaskMapper.getCompletedTasks(taskListParam);

        //定义获得未完成的任务列表
        List<ParentTask> UncompletedParentTask = parentTaskMapper.getUnCompletedTasks(taskListParam);

        return new TaskCompletionResult(TasktParamCopy(completedParentTask),TasktParamCopy(UncompletedParentTask));
    }

    @Override
    public ParentTask getParentTaskById(Long id) {
        ParentTask parentTask = parentTaskMapper.selectById(id);
        return parentTask;
    }

    public List<TaskParam> TasktParamCopy(List<ParentTask> parentTasks){

        List<TaskParam> taskParamList = new ArrayList<>();

        //将父任务列表内的属性包装成TaskParam
        for (int i = 0;i < parentTasks.size();i++){
            TaskParam taskParam = new TaskParam();
            ParentTask parentTask = parentTasks.get(i);

            //设置相应属性
            taskParam.setTitle(parentTask.getTitle());
            taskParam.setDescription(parentTask.getDescription());
            taskParam.setRewardPoints(parentTask.getRewardPoints());
            taskParam.setDeadTime(parentTask.getDeadTime());
            taskParam.setSubtasks(subtaskMapper.getSubtasksByParentTaskId(parentTask.getId()));
            taskParam.setId(parentTask.getId());

            //添加到参数对象列表中
            taskParamList.add(taskParam);
        }

        return taskParamList;
    }

}
