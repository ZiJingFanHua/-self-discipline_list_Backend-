package com.ljh.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.PointsDetails;
import com.ljh.project.entity.Userinfo;
import com.ljh.project.entity.param.PointArchiveParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.vo.PointsDetailVo;
import com.ljh.project.entity.vo.PointsListVo;
import com.ljh.project.mapper.PointsDetailsMapper;
import com.ljh.project.service.IPointsDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/***
 *Author zijing
 *Date 2023/5/5 16:43
 */
@Service
public class PointsDetailsServiceImpl extends ServiceImpl<PointsDetailsMapper, PointsDetails> implements IPointsDetailsService {

    @Resource
    PointsDetailsMapper pointsDetailsMapper;

    @Resource
    UserinfoServiceImpl userinfoService;
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
//        pointsDetailVo.setPointsDetails(pointsDetails);
        //计算总支出和总收入
        for (int i = 0; i<pointsDetails.size();i++){

            String amount = pointsDetails.get(i).getChangeAmount();
            if (amount.charAt(0) == '+'){
                revenue += Integer.parseInt(amount);
                if(pointArchiveParam.getType() == 2){
                    pointsDetails.remove(i);
                    i--;
                }
            }
            else if (amount.charAt(0) == '-'){
                spending += Integer.parseInt(amount);
                if(pointArchiveParam.getType() == 1){
                    pointsDetails.remove(i);
                    i--;
                }
            }

        }
        Userinfo user = userinfoService.getById(pointArchiveParam.getUserId());
        pointsDetailVo.setPointsDetails(pointsDetails);
        pointsDetailVo.setTotalRevenue(revenue);
        pointsDetailVo.setTotalSpending(Math.abs(spending));
        pointsDetailVo.setTotal(user.getAchievePoints());
        return new OkResult(pointsDetailVo);
    }


    /**
     * 根据type类型得到当前月份的支出或收入列表
     * @param type
     * @return
     */
    @Override
    public BaseResult getPointsByType(int type) {
        return null;
    }


    @Override
    public PointsListVo getPointsList(String userId) {
        PointsListVo pointsListVo = new PointsListVo();
        LocalDate now = LocalDate.now();
        LocalDate localDate1 = now.plusMonths(1);
        LocalDate localDate2 = localDate1.minusDays(localDate1.getDayOfMonth()-1);
        LocalDate localDate = localDate2.minusMonths(4);
        int monthValue = localDate.getMonthValue();
        Integer[] month = new Integer[4];
        Integer[] income = new Integer[4];
        Integer[] expenditure = new Integer[4];
        for(int i =0 ;i<4;i++){
            month[i] = monthValue +i;
            income[i] = 0;
            expenditure[i] = 0;
            LocalDate localDate3 = localDate.plusMonths(1);
            QueryWrapper<PointsDetails> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userId);
            queryWrapper.between("occurring_time",localDate,localDate3);
            localDate = localDate3;
            List<PointsDetails> pointsDetails = pointsDetailsMapper.selectList(queryWrapper);
            System.out.println(pointsDetails.size());
            for(int j = 0 ;j<pointsDetails.size();j++){
                String amount = pointsDetails.get(j).getChangeAmount();
                System.out.println(amount + "------------------------");
                if (amount.charAt(0) == '+'){
                    income[i] += Integer.parseInt(amount);
                }
                else if (amount.charAt(0) == '-'){
                    expenditure[i] += Integer.parseInt(amount);
                }
            }
        }

        pointsListVo.setExpenditure(expenditure);
        pointsListVo.setIncome(income);
        pointsListVo.setMonth(month);
        return pointsListVo;
    }
}
