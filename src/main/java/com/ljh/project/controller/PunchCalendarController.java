package com.ljh.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljh.project.entity.PunchCalendar;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.vo.DaysListVo;
import com.ljh.project.service.impl.PunchCalendarServiceImpl;
import com.ljh.project.utils.IsLeapYear;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Arrays;

/***
 *Author zijing
 *Date 2023/5/19 13:40
 */
@RestController
@RequestMapping("/punchCalendar")
public class PunchCalendarController {
    @Resource
    PunchCalendarServiceImpl punchCalendarService;

    @PostMapping("/getPunchCalendar")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "查询得到打卡日历信息")
    public BaseResult getPunchCalendar(@ApiParam("用户id") String userId,@ApiParam("年月，格式 2022-04") String yearMonth) {
        PunchCalendar punchCalendar = punchCalendarService.getOne(new QueryWrapper<PunchCalendar>().eq("user_id", userId).eq("year_mouth", yearMonth));

        char[] array;
        if (punchCalendar != null) {
            array = punchCalendar.getCalendar().toCharArray();
        } else {
            LocalDate now = LocalDate.now();
            int year = now.getYear();
            int monthValue = now.getMonthValue();
            String timeFormat = IsLeapYear.timeFormat(year, monthValue);
            punchCalendar = new PunchCalendar();
            int days = IsLeapYear.days(year, monthValue);
            char[] everyday = new char[days];
            System.out.println("这个月有多少天："+days);
            Arrays.fill(everyday, '0');
            System.out.println(String.valueOf(everyday));
            punchCalendar.setUserId(userId).setYearMouth(timeFormat)
                    .setCalendar(String.valueOf(everyday));
            boolean save = punchCalendarService.save(punchCalendar);
            array = everyday;
        }
        Character[] everydayStatusArr = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            everydayStatusArr[i] = array[i];
        }
        return new OkResult("查询成功！", everydayStatusArr);

    }

    @GetMapping("/getDaysList")
    @ApiOperation(value = "查询打卡天数列表")
    public BaseResult getDaysList(String id){
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.minusMonths(3);
        DaysListVo daysListVo = new DaysListVo();
        Integer[] month = new Integer[4];
        Integer[] days = new Integer[4];
        for(int i =0;i<4;i++){
            String yearMonth = localDate.getYear()+"-";
            month[i] = localDate.getMonthValue();
            days[i] = 0;
            if(localDate.getMonthValue()<10){
                yearMonth = yearMonth + "0"+localDate.getMonthValue();
            }else{
                yearMonth = yearMonth+localDate.getMonthValue();
            }
            PunchCalendar punchCalendar = punchCalendarService.getOne(new QueryWrapper<PunchCalendar>().eq("user_id", id).eq("year_mouth", yearMonth));
            if(punchCalendar != null) {
                String calendar = punchCalendar.getCalendar();
                System.out.println(calendar);
                for (int j = 0; j < calendar.length(); j++) {
                    if (calendar.charAt(j) == '1') {
                        days[i]++;
                    }
                }
            }
            localDate = localDate.plusMonths(1);
        }
        daysListVo.setDays(days);
        daysListVo.setMonth(month);
    return new OkResult("搜索成功",daysListVo);
    }

}
