package com.ljh.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.project.entity.Award;
import com.ljh.project.entity.param.AwardExchangeParam;

import java.util.List;

/***
 *Author zijing
 *Date 2023/4/18 15:46
 */
public interface AwardMapper extends BaseMapper<Award> {

    //List<Award> getAwardList(String id);

    List<Award> getAwardList(String userId);

    Award getAwardById(AwardExchangeParam awardExchangeParam);
}
