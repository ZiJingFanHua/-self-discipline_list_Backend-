package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.PointsDetails;
import com.ljh.project.entity.param.PointArchiveParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.vo.PointsListVo;

/***
 *Author zijing
 *Date 2023/5/6 11:30
 */
public interface IPointsDetailsService extends IService<PointsDetails> {

    BaseResult getArchive(String userId);

    BaseResult getArchiveBydate(PointArchiveParam pointArchiveParam);

    BaseResult getPointsByType(int type);

    PointsListVo getPointsList(String userId);
}
