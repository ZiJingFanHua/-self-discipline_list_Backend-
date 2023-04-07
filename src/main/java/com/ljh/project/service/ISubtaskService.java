package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.Subtask;

/**
 * <p>
 * 子任务表 服务类
 * </p>
 *
 * @author 木心
 * @since 2022-04-21
 */
public interface ISubtaskService extends IService<Subtask> {
    public boolean insertSubtask(Subtask subtask);
}
