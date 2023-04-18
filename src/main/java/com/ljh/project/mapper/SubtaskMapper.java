package com.ljh.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.project.entity.Subtask;

import java.util.List;

/***
 *Author zijing
 *Date 2023/4/18 15:38
 */
public interface SubtaskMapper extends BaseMapper<Subtask> {

    /**
     * 创建子任务
     * @param fatherId
     * @return
     */
    List<Subtask> getSubtasksByParentTaskId(Long fatherId);

}
