package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.SmallTask;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 木心
 * @since 2022-01-16
 */
public interface ISmallTaskService extends IService<SmallTask> {
    public Integer insert(SmallTask smallTask);
}
