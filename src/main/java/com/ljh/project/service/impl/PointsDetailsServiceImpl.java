package com.ljh.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.PointsDetails;
import com.ljh.project.entity.param.PointArchiveParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.vo.PointsDetailVo;
import com.ljh.project.mapper.PointsDetailsMapper;
import com.ljh.project.service.IPointsDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 积分详情 服务实现类
 * </p>
 *
 * @author 木心
 * @since 2022-04-18
 */
@Service
public class PointsDetailsServiceImpl extends ServiceImpl<PointsDetailsMapper, PointsDetails> implements IPointsDetailsService {

    @Resource
    PointsDetailsMapper pointsDetailsMapper;


    /**
     * 获得归档信息(年份)
     * @return
     */
    @Override
    public BaseResult getArchive(String userId){

        //获得有积分记录的年份
        Integer[] years = pointsDetailsMapper.getArchive(userId);
        //进行从小到大的排序
        Arrays.sort(years);

        return new OkResult(years);
    }


    /**
     * 获得指定年月的明细表
     * @param pointArchiveParam
     * @return
     */
    @Override
    public BaseResult getArchiveBydate(PointArchiveParam pointArchiveParam){

        PointsDetailVo pointsDetailVo = new PointsDetailVo();
        int revenue = pointsDetailVo.getTotalRevenue();
        int spending = pointsDetailVo.getTotalSpending();

        List<PointsDetails> pointsDetails = pointsDetailsMapper.getArchiveBydate(pointArchiveParam);

        pointsDetailVo.setPointsDetails(pointsDetails);

        //计算总支出和总收入
        for (int i = 0; i<pointsDetails.size();i++){

            String amount = pointsDetails.get(i).getChangeAmount();
            if (amount.charAt(0) == '+'){
                revenue += Integer.parseInt(amount);
            }
            else if (amount.charAt(0) == '-'){
                spending += Integer.parseInt(amount);
            }

        }

        pointsDetailVo.setTotalRevenue(revenue);
        pointsDetailVo.setTotalSpending(Math.abs(spending));

        return new OkResult(pointsDetailVo);
    }

}
