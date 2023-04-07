package com.ljh.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.Subtask;
import com.ljh.project.mapper.SubtaskMapper;
import com.ljh.project.service.ISubtaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 子任务表 服务实现类
 * </p>
 *
 * @author 木心
 * @since 2022-04-21
 */
@Service
public class SubtaskServiceImpl extends ServiceImpl<SubtaskMapper, Subtask> implements ISubtaskService {

    @Resource
    SubtaskMapper subtaskMapper;

    @Override
    public boolean insertSubtask(Subtask subtask) {
        int insertNumber = subtaskMapper.insert(subtask);
        return insertNumber != 0;
    }
}
