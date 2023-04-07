package com.ljh.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.SmallTask;
import com.ljh.project.mapper.SmallTaskMapper;
import com.ljh.project.service.ISmallTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 木心
 * @since 2022-01-16
 */
@Service
public class SmallTaskServiceImpl extends ServiceImpl<SmallTaskMapper, SmallTask> implements ISmallTaskService {
    @Resource
    SmallTaskMapper smallTaskMapper;
    @Override
    public Integer insert(SmallTask smallTask) {
        return smallTaskMapper.insert(smallTask);
    }
}
