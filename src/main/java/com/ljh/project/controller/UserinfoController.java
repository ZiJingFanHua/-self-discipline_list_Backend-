package com.ljh.project.controller;


import com.ljh.project.annotation.Authorize;
import com.ljh.project.entity.Token;
import com.ljh.project.entity.Userinfo;
import com.ljh.project.entity.WeChatLoginParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.entity.result.WeChatLoginResult;
import com.ljh.project.service.impl.UserinfoServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 木心
 * @since 2022-01-16
 */
@RestController
@RequestMapping("/userinfo")
public class UserinfoController {
    @Resource
    UserinfoServiceImpl userinfoService;

    /**
     * 微信小程序登录
     *
     * 登录成功后，将用户身份信息及session_key存入token
     * @param
     * @return
     */

    @Authorize(required = false)
    @PostMapping("/weChatLogin")
    public BaseResult weChatLogin(@RequestBody WeChatLoginParam weChatLoginParam){

//        SingleResult<String> result = new SingleResult<String>();
//        //第三步：调用service.weChatLogin(model):后台检查openid是否存在，返回openid对应的用户
//        WeChatLoginResult<UserAccount> loginResult = service.weChatLogin(model);

        System.out.println("weChatLoginParam:"+weChatLoginParam);
        WeChatLoginResult weChatLoginResult = userinfoService.weChatLogin(weChatLoginParam);

        //第四步：
        Userinfo user = (Userinfo) weChatLoginResult.getData();
        if(user == null ){
            weChatLoginResult.setCode(400);
            weChatLoginResult.setMsg("登录失败");
        }
        else {
//            User u = new User();
//            u.setId(user.getId());
//            u.setPassword(user.getPassword() == null ? user.getWxopenid() : user.getPassword());
//            u.setSessionKey(loginResult.getSession_key());
            Token tokens = new Token();
            tokens.setOpenid(weChatLoginResult.getOpenid()).setSession_key(weChatLoginResult.getSession_key());
            String token = tokens.getToken();
            weChatLoginResult.setToken(token);
            weChatLoginResult.setCode(200);
            weChatLoginResult.setMsg("登陆成功");
        }

        return weChatLoginResult;
    }

    @GetMapping("/getMessage")
    @ApiOperation("获取用户积分点和坚持天数接口")
    public BaseResult getMessage(@ApiParam("用户id") String userId) {
        Userinfo userinfo = userinfoService.getById(userId);
        return new OkResult("查询成功！", userinfo);
    }
    @Authorize
    @PostMapping("/postTester")
    public BaseResult postTester() {
        return new OkResult();
    }
    @PostMapping("/test")
    public BaseResult test() {
        return new OkResult();
    }
}

