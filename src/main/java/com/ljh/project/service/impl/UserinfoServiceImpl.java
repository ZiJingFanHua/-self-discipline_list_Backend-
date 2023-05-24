package com.ljh.project.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.project.entity.*;
import com.ljh.project.entity.param.TaskListParam;
import com.ljh.project.entity.param.UserInfoParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.result.WeChatLoginResult;
import com.ljh.project.entity.vo.AccountMessageVo;
import com.ljh.project.mapper.ParentTaskMapper;
import com.ljh.project.mapper.UserinfoMapper;
import com.ljh.project.service.IUserinfoService;
import com.ljh.project.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

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

    @Resource
    ParentTaskMapper parentTaskMapper;

    @Autowired
    SubjectServiceImpl subjectService;
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    LikeServiceImpl likeService;
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

    /***
     * 设置用户名称以及头像
     * @param userInfoParam
     * @return
     */
    @Override
    public BaseResult setUserInfo(UserInfoParam userInfoParam) {
        Userinfo userinfo = userinfoMapper.getUserInfoById(userInfoParam.getId());
        userinfo.setNickname(userInfoParam.getNickname());
        userinfo.setHeadPhoto(userInfoParam.getHeadPhoto());
        userinfoMapper.updateById(userinfo);
        return new OkResult("修改成功",null);
    }

    /***
     * 得到用户主页信息
     * @return
     */
    @Override
    public AccountMessageVo getAccountMessage(String id) {
        AccountMessageVo accountMessageVo = new AccountMessageVo();
        Userinfo userInfo = userinfoMapper.getUserInfoById(id);
        accountMessageVo.setDays(userInfo.getDays());
        accountMessageVo.setTotal(userInfo.getAchievePoints());
        LocalDate deadTime = LocalDate.now();
        //获取未完成任务列表
        List<ParentTask> unCompletedParentTask = parentTaskMapper.getUnCompletedTasks(new TaskListParam(id,deadTime));
        BigTask task = null;
        if(unCompletedParentTask.size()!=0){
            task = new BigTask();
            task.setTitle(unCompletedParentTask.get(0).getTitle());
            task.setDescription(unCompletedParentTask.get(0).getDescription());
        }

        //获取发表话题
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("publisher_id",id);
        List<Subject> list = subjectService.list(queryWrapper);

        //获取回复数量

        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("form_id",id);
        commentQueryWrapper.eq("look",0);
        List<Comment> list1 = commentService.list(commentQueryWrapper);

        //获取喜欢的话题
        QueryWrapper<Like> likeQueryWrapper = new QueryWrapper<>();
        likeQueryWrapper.eq("account_id",id);
        likeQueryWrapper.isNotNull("subject_id");
        List<Like> list2 = likeService.list(likeQueryWrapper);

        accountMessageVo.setLikes(list2.size());
        accountMessageVo.setComments(list1.size());
        accountMessageVo.setTalkNums(list.size());
        accountMessageVo.setTask(task);
        return accountMessageVo;
    }
}
