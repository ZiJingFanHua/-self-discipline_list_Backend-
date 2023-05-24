package com.ljh.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.ParentTask;
import com.ljh.project.entity.PointsDetails;
import com.ljh.project.entity.param.TaskListParam;
import com.ljh.project.entity.param.TaskParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.TaskCompletionResult;
import com.ljh.project.entity.vo.TaskListVo;
import com.ljh.project.mapper.ParentTaskMapper;
import com.ljh.project.mapper.SubtaskMapper;
import com.ljh.project.service.IParentTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/***
 *Author zijing
 *Date 2023/5/18 12:24
 */
@Service
public class ParentTaskServiceImpl extends ServiceImpl<ParentTaskMapper, ParentTask> implements IParentTaskService {

    @Resource
    ParentTaskMapper parentTaskMapper;

    @Resource
    SubtaskMapper subtaskMapper;

    @Resource
    ParentTaskServiceImpl parentTaskService;

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


    /**
     * 获取完成任务列表
     * @param taskListParam
     * @return
     */
    @Override
    public List<TaskParam> getCreatedTaskList(TaskListParam taskListParam) {
        List<ParentTask> completedParentTask = parentTaskMapper.getCompletedTasks(taskListParam);
        List<TaskParam> taskParams = TasktParamCopy(completedParentTask);
        return taskParams;
    }

    @Override
    public TaskListVo getTaskListDate(String id) {
        int time = 4;
        LocalDate now = LocalDate.now();
        LocalDate localDate1 = now.plusMonths(1);
        LocalDate localDate2 = localDate1.minusDays(localDate1.getDayOfMonth()-1);
        LocalDate localDate = localDate2.minusMonths(time);
        Integer[] month = new Integer[time];
        Integer[] complete = new Integer[time];
        Integer[] unComplete = new Integer[time];
        for(int i=0;i<time;i++){
            month[i] = localDate.getMonthValue();
            LocalDate localDate3 = localDate.plusMonths(1);
            QueryWrapper<ParentTask> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",id);
            queryWrapper.eq("status",1);
            queryWrapper.between("fininshed_time",localDate,localDate3);
            List<ParentTask> list = parentTaskService.list(queryWrapper);
            complete[i] = list.size();

            QueryWrapper<ParentTask> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("user_id",id);
            queryWrapper1.eq("status",0);
            queryWrapper1.between("dead_time",localDate,localDate3);
            List<ParentTask> list1 = parentTaskService.list(queryWrapper1);
            unComplete[i] = list1.size();

            localDate = localDate3;
        }
        TaskListVo taskListVo = new TaskListVo();
        taskListVo.setMonth(month);
        taskListVo.setComplete(complete);
        taskListVo.setUnComplete(unComplete);
        return taskListVo;
    }
}
