package com.ljh.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljh.project.entity.ParentTask;
import com.ljh.project.entity.PointsDetails;
import com.ljh.project.entity.PunchCalendar;
import com.ljh.project.entity.Subtask;
import com.ljh.project.entity.param.TaskListParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.result.ServerErrResult;
import com.ljh.project.service.impl.*;
import com.ljh.project.utils.IsLeapYear;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 父任务表 前端控制器
 * </p>
 *
 * @author 木心
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/parentTask")
@Log4j
@Api(description = "父任务操作相关接口")
public class ParentTaskController {
    @Resource
    ParentTaskServiceImpl parentTaskService;
    @Resource
    SubtaskServiceImpl subtaskService;
    @Resource
    PointsDetailsServiceImpl pointsDetailsService;
    @Resource
    PunchCalendarServiceImpl punchCalendarService;
    @Resource
    UserinfoServiceImpl userinfoService;


    @PostMapping("/insertTask")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "创建父任务与子任务")
    public BaseResult insertTask(@RequestBody ParentTask parentTask) {
        ParentTask parentTaskCopy = new ParentTask();
        System.out.println(parentTask);
        BeanUtils.copyProperties(parentTask,parentTaskCopy);
        try {
            List<Subtask> subtaskList = parentTaskCopy.getSubtaskList();
            parentTask.setUnfinishedNumber(subtaskList.size());
            Long taskId = parentTaskService.insertParentTask(parentTask);
            System.out.println("parentTask.getid:"+parentTask.getId());
            for (Subtask subtask : subtaskList) {
                subtask.setFatherId(parentTask.getId());
                if (!subtaskService.insertSubtask(subtask)) {
                    throw new Exception("插入子任务失败！");
                }
            }
        } catch (Exception e) {
            log.error(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return new ServerErrResult(e.getMessage());
        }
        return new OkResult("创建成功！", "");
    }

    @PostMapping("/tasklist")
    @ApiOperation(value = "查询任务列表")
    public BaseResult getTaskList(@RequestBody TaskListParam taskListParam){
        return parentTaskService.getTaskList(taskListParam);
    }

    @PostMapping("/missionAccomplished")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "点击完成父任务接口，修改父子任务状态，记录积分细节，记录或修改日历打卡记录")
    public BaseResult missionAccomplished(@ApiParam("父任务的id") Long id) {
        ParentTask parentTaskById = parentTaskService.getParentTaskById(id);
        try {
            parentTaskById.setStatus(1).setUnfinishedNumber(0);
            boolean updateById = parentTaskService.updateById(parentTaskById);
            if (!updateById) {
                throw new Exception("父任务修改状态失败！");
            }
            List<Subtask> subtaskList = subtaskService.list(new QueryWrapper<Subtask>().eq("father_id", id).eq("status",0));
            for (Subtask subtask : subtaskList) {
                subtask.setStatus(1);
                boolean updateSubTask = subtaskService.updateById(subtask);
                if (!updateSubTask) {
                    throw new Exception("修改子任务状态失败！");
                }
            }
            boolean addPoints = userinfoService.addPoints(parentTaskById.getUserId(), parentTaskById.getRewardPoints());
            if (!addPoints) {
                throw new Exception("添加奖励点失败！");
            }
            PointsDetails pointsDetails = new PointsDetails();
            pointsDetails.setUserId(parentTaskById.getUserId()).setEvent(parentTaskById.getTitle()+"任务奖励")
                    .setChangeAmount("+"+parentTaskById.getRewardPoints()).setOccurringTime(LocalDateTime.now());
            boolean savePointsDetails = pointsDetailsService.save(pointsDetails);
            if (!savePointsDetails) {
                throw new Exception("保存奖励点详细失败！");
            }

            LocalDate now = LocalDate.now();
            int year = now.getYear();
            int monthValue = now.getMonthValue();
            String timeFormat = IsLeapYear.timeFormat(year, monthValue);
            PunchCalendar punchCalendar = punchCalendarService.getOne(new QueryWrapper<PunchCalendar>().eq("user_id", parentTaskById.getUserId()).eq("year_mouth", timeFormat));
            int dayOfMonth = now.getDayOfMonth();
            if (punchCalendar != null) {
                if (punchCalendar.getCalendar().charAt(dayOfMonth - 1) == '0') {
                    StringBuilder stringBuilder = new StringBuilder(punchCalendar.getCalendar());
                    stringBuilder.replace(dayOfMonth - 1, dayOfMonth, "1");
                    punchCalendar.setCalendar(stringBuilder.toString());
                    boolean update = punchCalendarService.updateById(punchCalendar);
                    if (!update) {
                        throw new Exception("更新日历失败");
                    }
                    boolean daysAddOne = userinfoService.daysAddOne(parentTaskById.getUserId());
                }
            } else {
                PunchCalendar calendar = new PunchCalendar();
                int days = IsLeapYear.days(year, monthValue);
                char[] everyday = new char[days];
                System.out.println("这个月有多少天："+days);
                Arrays.fill(everyday, '0');
                System.out.println("今天是第几天："+dayOfMonth);
                everyday[dayOfMonth - 1] = '1';
                System.out.println(String.valueOf(everyday));
                calendar.setUserId(parentTaskById.getUserId()).setYearMouth(timeFormat)
                        .setCalendar(String.valueOf(everyday));
                boolean save = punchCalendarService.save(calendar);
                if (!save) {
                    throw new Exception("创建日历失败！");
                }
                boolean daysAddOne = userinfoService.daysAddOne(parentTaskById.getUserId());
            }


        } catch (Exception e) {
            log.error(e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServerErrResult(e.getMessage());
        }
        return new OkResult("修改成功！", "");
    }
}
