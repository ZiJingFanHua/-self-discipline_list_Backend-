package com.ljh.project.controller;


import com.ljh.project.annotation.Authorize;
import com.ljh.project.entity.SmallTask;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.result.ServerErrResult;
import com.ljh.project.service.impl.SmallTaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 木心
 * @since 2022-01-16
 */
@RestController
@RequestMapping("/smallTask")
@Slf4j
public class SmallTaskController {
    @Resource
    SmallTaskServiceImpl smallTaskServiceImpl;

    @Authorize
    @PostMapping("/createDayTask")
    @Transactional(rollbackFor = Exception.class)
    public BaseResult createDayTask(@RequestBody SmallTask smallTask) {
        try {
            Integer insertNumber = smallTaskServiceImpl.insert(smallTask);
            if (insertNumber == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ServerErrResult("小任务创建失败！");
            }
            return new OkResult("创建小任务成功！");

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("小任务创建失败", e);
            return new ServerErrResult("小任务创建失败！");
        }
    }

}
