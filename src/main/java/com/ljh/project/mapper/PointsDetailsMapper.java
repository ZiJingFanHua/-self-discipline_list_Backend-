package com.ljh.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.project.entity.PointsDetails;
import com.ljh.project.entity.param.PointArchiveParam;

import java.util.List;

/***
 *Author zijing
 *Date 2023/4/18 15:47
 */
public interface PointsDetailsMapper extends BaseMapper<PointsDetails> {

    Integer[] getArchive(String userId);

    List<PointsDetails> getArchiveBydate(PointArchiveParam pointArchiveParam);
}
