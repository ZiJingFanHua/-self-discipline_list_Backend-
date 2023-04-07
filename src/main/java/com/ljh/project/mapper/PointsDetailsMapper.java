package com.ljh.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.project.entity.PointsDetails;
import com.ljh.project.entity.param.PointArchiveParam;

import java.util.List;

/**
 * <p>
 * 积分详情 Mapper 接口
 * </p>
 *
 * @author 木心
 * @since 2022-04-18
 */
public interface PointsDetailsMapper extends BaseMapper<PointsDetails> {

    Integer[] getArchive(String userId);

    List<PointsDetails> getArchiveBydate(PointArchiveParam pointArchiveParam);
}
