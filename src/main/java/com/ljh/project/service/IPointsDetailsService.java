package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.PointsDetails;
import com.ljh.project.entity.param.PointArchiveParam;
import com.ljh.project.entity.result.BaseResult;

/**
 * <p>
 * 积分详情 服务类
 * </p>
 *
 * @author 木心
 * @since 2022-04-18
 */
public interface IPointsDetailsService extends IService<PointsDetails> {

    BaseResult getArchive(String userId);

    BaseResult getArchiveBydate(PointArchiveParam pointArchiveParam);
}
