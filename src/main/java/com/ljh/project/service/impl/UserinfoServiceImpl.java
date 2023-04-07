package com.ljh.project.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.Userinfo;
import com.ljh.project.entity.WeChatLoginParam;
import com.ljh.project.entity.result.WeChatLoginResult;
import com.ljh.project.mapper.UserinfoMapper;
import com.ljh.project.service.IUserinfoService;
import com.ljh.project.utils.HttpClientUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 木心
 * @since 2022-01-16
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements IUserinfoService {
    @Resource
    UserinfoMapper userinfoMapper;
    @Override
    public WeChatLoginResult weChatLogin(WeChatLoginParam weChatLoginParam) {
        WeChatLoginResult weChatLoginResult = null;
        System.out.println("weChatLoginParam:"+weChatLoginParam);
        if (weChatLoginParam.getJs_code() == null) {
            weChatLoginResult = new WeChatLoginResult();
            weChatLoginResult.setCode(400);
            weChatLoginResult.setMsg("参数有问题！");
            return weChatLoginResult;
        }
        try {

          // code  -> openid
            String url = weChatLoginParam.getUrl();
            System.out.println("url:"+url);
            JSONObject jsonObject = HttpClientUtils.httpGet(url);
            System.out.println("json:"+jsonObject);
            weChatLoginResult = jsonObject.toJavaObject(WeChatLoginResult.class);
            System.out.println("weChatLoginResult:"+weChatLoginResult);
            //将json字符串转化成对象
            if(weChatLoginResult.getErrcode() == null){
                // 去数据库 检查 openId 是否存在 不存在就新建用户
                Userinfo user = userinfoMapper.selectById(weChatLoginResult.getOpenid());
                if(user == null || user.getId() == null){
                    // 不存在，就是第一次登录：新建用户信息
                    user = new Userinfo();
                    user.setId(weChatLoginResult.getOpenid());
//                    user.setWxopenid(result.getOpenid());
//                    user.setLasttime(new Date());
                    userinfoMapper.insert(user);
                }
                else {
                    //如果存在，就不是第一次登录，更新最后登录时间
//                    user.setLasttime(new Date());
//                    userAccount.updateByPrimaryKeySelective(user);
                }
                weChatLoginResult.setData(user);

                // 保存登录日志
//                LoginLog log = new LoginLog();
//                log.setId(UUID.randomUUID().toString());
//                log.setCreatetime(new Date());
//                log.setLogindate(new Date());
//                log.setUserid(user.getId());
//                log.setWxcode(model.getCode());
//                loginLog.insert(log);
            }
            else {
                System.out.println(jsonObject);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return weChatLoginResult;

    }

    @Override
    public boolean daysAddOne(String id) {
        return userinfoMapper.daysAddOne(id);
    }

    @Override
    public boolean addPoints(String id, Integer points) {
        return userinfoMapper.addPoints(id, points);
    }
}
