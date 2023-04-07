package com.ljh.project.filter;

import com.ljh.project.annotation.Authorize;
import com.ljh.project.entity.Token;
import com.ljh.project.entity.Userinfo;
import com.ljh.project.service.impl.UserinfoServiceImpl;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: 木心
 * @Date:
 *
 * 获取token并验证token
 **/
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Resource
    UserinfoServiceImpl userService;
 
    //拦截器：请求之前preHandle
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        System.out.println("preHandle");

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
 
        //检查是否有passtoken注释，有则跳过认证，注意:其中这个注解多余了
//        if (method.isAnnotationPresent(AllowAnonymous.class)) {
//            AllowAnonymous passToken = method.getAnnotation(AllowAnonymous.class);
//            if (passToken.required()) {
//                return true;
//            }
//        }
        //检查有没有需要用户权限的注解
        //如果有注解Authorize，就需要验证token
        if (method.isAnnotationPresent(Authorize.class)) {
            System.out.println("token start!");
            Authorize userLoginToken = method.getAnnotation(Authorize.class);
            if (userLoginToken.required()) {
 
                String token = httpServletRequest.getHeader("authorization");// 从 http 请求头中取出 token

                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                Token tokens = new Token(token);
                // 获取 token 中的 user id
                String userId;
                try {
                    // 获取 userid
                    userId = tokens.getOpenid();
                    // 添加request参数，用于传递userid
                    httpServletRequest.setAttribute("currentUser", userId);
                    // 根据userId 查询用户信息
                    Userinfo user = userService.getById(userId);
                    if (user == null) {
                        throw new RuntimeException("用户不存在，请重新登录");
                    }
 
                    try {
                        String session_key = tokens.getSession_key();
                        // 添加request参数，用于传递userid
                        httpServletRequest.setAttribute("sessionKey", session_key);
                    }
                    catch (Exception e){
                    }
 
                    // 验证 密码
//                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//                    try {
//                        jwtVerifier.verify(token);
//                    } catch (JWTVerificationException e) {
//                        throw new RuntimeException("401");
//                    }
                } catch (Exception j) {
                    throw new RuntimeException("401");
                }
 
                return true;
            }
        }
        return true;
    }
 
 
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
 
    }
 
    //拦截器：请求之后：afterCompletion
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
