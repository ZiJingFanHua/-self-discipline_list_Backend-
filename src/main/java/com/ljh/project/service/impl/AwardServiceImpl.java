package com.ljh.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.Award;
import com.ljh.project.entity.PointsDetails;
import com.ljh.project.entity.Userinfo;
import com.ljh.project.entity.param.AwardExchangeParam;
import com.ljh.project.entity.param.AwardParam;
import com.ljh.project.entity.param.AwardSearchParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.ExchangeFailResult;
import com.ljh.project.entity.result.NoFindingErrResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.mapper.AwardMapper;
import com.ljh.project.mapper.PointsDetailsMapper;
import com.ljh.project.mapper.UserinfoMapper;
import com.ljh.project.service.IAwardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 木心
 * @since 2022-01-16
 */
@Transactional(rollbackFor = RuntimeException.class)
@Service
public class AwardServiceImpl extends ServiceImpl<AwardMapper, Award> implements IAwardService {

    @Resource
    AwardMapper awardMapper;

    @Resource
    UserinfoMapper userinfoMapper;

    @Resource
    PointsDetailsMapper pointsDetailsMapper;


    @Override
    public BaseResult getAwardList(String userId){
        List<Award> awardList = awardMapper.getAwardList(userId);
        return new OkResult(awardList);
    }

    @Override
    public BaseResult exchangeAward(AwardExchangeParam awardExchangeParam){
        System.out.println(awardExchangeParam.getAwardId() + "    " + awardExchangeParam.getUserId());

        //找到对应的奖励
        Award award = awardMapper.getAwardById(awardExchangeParam);
        //Userinfo userinfo = userinfoMapper.getUserInfoById(userId);

        //如果没找到返回错误
        if (award == null){
            System.out.println("award" + award);
            return new NoFindingErrResult();
        }

        //如果找到了则进行奖励点的删除操作
        Long achievePoint = userinfoMapper.getAchievePointById(awardExchangeParam.getUserId());


        //如果此时奖励点不够
        if (achievePoint < award.getExchangePoints()){
            return new ExchangeFailResult();
        }

        //如果奖励点够兑换
        achievePoint -= award.getExchangePoints();

        //更新用户奖励点数
        UpdateWrapper<Userinfo> updateWrapper = new UpdateWrapper<>();

        updateWrapper.eq("id",awardExchangeParam.getUserId()).set("achieve_points",achievePoint);

        userinfoMapper.update(null,updateWrapper);
        awardMapper.deleteById(awardExchangeParam.getAwardId());

        //将兑换的添加到积分明细中
        setAwardPointDetail(award);

        return new OkResult("奖励兑换成功",awardExchangeParam.getUserId());

    }

    @Override
    public BaseResult setAward(AwardParam awardParam){

        Award award = new Award();
        award.setDescription(awardParam.getDescription());
        award.setExchangePoints(awardParam.getExchangePoints());
        award.setUserId(awardParam.getUserId());
        awardMapper.insert(award);

        return new OkResult("奖励设置成功",award.getId());

    }

    @Override
    public BaseResult deleteAward(Long awardId) {

        if (awardMapper.selectById(awardId) == null){
            return new NoFindingErrResult();
        }
        awardMapper.deleteById(awardId);
        return new OkResult("奖励删除成功",awardId);


    }

    /**
     * 奖励添加到积分明细
     * @param award
     */
    public void setAwardPointDetail(Award award){

        PointsDetails pointsDetails = new PointsDetails();

        pointsDetails.setUserId(award.getUserId());
        pointsDetails.setEvent(award.getDescription());
        pointsDetails.setChangeAmount("-" + award.getExchangePoints());
        pointsDetails.setOccurringTime(LocalDateTime.now());

        pointsDetailsMapper.insert(pointsDetails);

    }

    /**
     * 通过模糊查询搜索奖励
     */
    @Override
    public BaseResult searchAward(AwardSearchParam awardSearchParam){
        QueryWrapper<Award> queryWrapper = new QueryWrapper<>();

        queryWrapper.like("description",awardSearchParam.getDescription());
        queryWrapper.eq("user_id",awardSearchParam.getUserId());

        List<Award> awardList = awardMapper.selectList(queryWrapper);

        return new OkResult(awardList);
    }



}
