package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.Award;
import com.ljh.project.entity.param.AwardExchangeParam;
import com.ljh.project.entity.param.AwardParam;
import com.ljh.project.entity.param.AwardSearchParam;
import com.ljh.project.entity.result.BaseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 木心
 * @since 2022-01-16
 */
public interface IAwardService extends IService<Award> {

    /**
     * 查询对应用户的奖励列表
     * @param userId
     * @return
     */
    public BaseResult getAwardList(String userId);

    /**
     * 尝试兑换奖励
     */
    public BaseResult exchangeAward(AwardExchangeParam awardExchangeParam);

    /**
     * 设置奖励
     */
    public BaseResult setAward(AwardParam awardParam);

    /**
     * 删除奖励
     */
    public BaseResult deleteAward(Long awardId);

    /**
     * 搜索奖励
     */
    public BaseResult searchAward(AwardSearchParam awardSearchParam);
}
