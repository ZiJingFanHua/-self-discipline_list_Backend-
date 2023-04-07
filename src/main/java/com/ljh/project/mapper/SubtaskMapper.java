package com.ljh.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.project.entity.Subtask;

import java.util.List;

/**
 * <p>
 * 子任务表 Mapper 接口
 * </p>
 *
 * @author 木心
 * @since 2022-04-21
 */
public interface SubtaskMapper extends BaseMapper<Subtask> {

    /**
     * 创建子任务
     * @param fatherId
     * @return
     */
    List<Subtask> getSubtasksByParentTaskId(Long fatherId);

}
