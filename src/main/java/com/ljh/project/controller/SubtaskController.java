package com.ljh.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljh.project.entity.ParentTask;
import com.ljh.project.entity.PunchCalendar;
import com.ljh.project.entity.Subtask;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.result.ServerErrResult;
import com.ljh.project.service.impl.ParentTaskServiceImpl;
import com.ljh.project.service.impl.PunchCalendarServiceImpl;
import com.ljh.project.service.impl.SubtaskServiceImpl;
import com.ljh.project.service.impl.UserinfoServiceImpl;
import com.ljh.project.utils.IsLeapYear;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Arrays;

/***
 *Author zijing
 *Date 2023/5/6 14:52
 */
@RestController
@Log4j
@RequestMapping("/subtask")
public class SubtaskController {
    @Resource
    SubtaskServiceImpl subtaskService;

    @Resource
    ParentTaskServiceImpl parentTaskService;
    @Resource
    PunchCalendarServiceImpl punchCalendarService;
    @Resource
    UserinfoServiceImpl userinfoService;

//    @Authorize
    @PostMapping("/missionAccomplished")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "点击完成子任务接口")
    public BaseResult missionAccomplished(@ApiParam("子任务的id") Long id) {

        System.out.println("idi"+id);
        try {
            Subtask subtask = subtaskService.getById(id);
            ParentTask parentTask = parentTaskService.getParentTaskById(subtask.getFatherId());
            subtask.setStatus(1);
            boolean updateSubTaskStatus = subtaskService.updateById(subtask);
            if (!updateSubTaskStatus) {
                throw new Exception("更新小任务失败！");
            }
            parentTask.setUnfinishedNumber(parentTask.getUnfinishedNumber() - 1);
            if (parentTask.getUnfinishedNumber() <= 0) {
                parentTask.setStatus(1).setFinishedTime(LocalDate.now());
                boolean addPoints = userinfoService.addPoints(parentTask.getUserId(), parentTask.getRewardPoints());
                if (!addPoints) {
                    throw new Exception("添加奖励点失败！");
                }
            }
            boolean update = parentTaskService.updateById(parentTask);
            if (!update) {
                throw new Exception("更新父任务未完成子任务数或状态失败！");
            }
            LocalDate now = LocalDate.now();
            int year = now.getYear();
            int monthValue = now.getMonthValue();
            String timeFormat = IsLeapYear.timeFormat(year, monthValue);
            PunchCalendar punchCalendar = punchCalendarService.getOne(new QueryWrapper<PunchCalendar>().eq("user_id", parentTask.getUserId()).eq("year_mouth", timeFormat));
            int dayOfMonth = now.getDayOfMonth();
            if (punchCalendar != null) {
                if (punchCalendar.getCalendar().charAt(dayOfMonth - 1) == '0') {
                    StringBuilder stringBuilder = new StringBuilder(punchCalendar.getCalendar());
                    stringBuilder.replace(dayOfMonth - 1, dayOfMonth, "1");
                    punchCalendar.setCalendar(stringBuilder.toString());
                    boolean updateStatus = punchCalendarService.updateById(punchCalendar);
                    if (!updateStatus) {
                        throw new Exception("更新日历失败");
                    }
                    boolean daysAddOne = userinfoService.daysAddOne(parentTask.getUserId());
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
                calendar.setUserId(parentTask.getUserId()).setYearMouth(timeFormat)
                        .setCalendar(String.valueOf(everyday));
                boolean save = punchCalendarService.save(calendar);
                if (!save) {
                    throw new Exception("创建日历失败！");
                }
                boolean daysAddOne = userinfoService.daysAddOne(parentTask.getUserId());
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServerErrResult(e.getMessage());
        }
        return new OkResult("修改成功！", "");
    }
}
