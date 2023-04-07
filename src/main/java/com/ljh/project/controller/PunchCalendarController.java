package com.ljh.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljh.project.entity.PunchCalendar;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.service.impl.PunchCalendarServiceImpl;
import com.ljh.project.utils.IsLeapYear;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * <p>
 * 打卡日历记录 前端控制器
 * </p>
 *
 * @author 木心
 * @since 2022-04-18
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
}
