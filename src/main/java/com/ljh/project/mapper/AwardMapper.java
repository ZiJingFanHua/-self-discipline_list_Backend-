package com.ljh.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.project.entity.Award;
import com.ljh.project.entity.param.AwardExchangeParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 木心
 * @since 2022-01-16
 */
public interface AwardMapper extends BaseMapper<Award> {

    //List<Award> getAwardList(String id);

    List<Award> getAwardList(String userId);

    Award getAwardById(AwardExchangeParam awardExchangeParam);
}
