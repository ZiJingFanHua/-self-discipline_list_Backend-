package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.Userinfo;
import com.ljh.project.entity.WeChatLoginParam;
import com.ljh.project.entity.param.UserInfoParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.WeChatLoginResult;
import com.ljh.project.entity.vo.AccountMessageVo;

/***
 *Author zijing
 *Date 2023/5/6 11:12
 * 用户服务类
 */
public interface IUserinfoService extends IService<Userinfo> {
    public WeChatLoginResult weChatLogin(WeChatLoginParam weChatLoginParam);

    boolean daysAddOne(String id);
    boolean addPoints(String id,Integer points);
    public BaseResult setUserInfo(UserInfoParam userInfoParam);

    public AccountMessageVo getAccountMessage(String id);
}
