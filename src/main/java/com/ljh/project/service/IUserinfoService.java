package com.ljh.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.project.entity.Userinfo;
import com.ljh.project.entity.WeChatLoginParam;
import com.ljh.project.entity.result.WeChatLoginResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 木心
 * @since 2022-01-16
 */
public interface IUserinfoService extends IService<Userinfo> {
    public WeChatLoginResult weChatLogin(WeChatLoginParam weChatLoginParam);

    boolean daysAddOne(String id);
    boolean addPoints(String id,Integer points);
}
