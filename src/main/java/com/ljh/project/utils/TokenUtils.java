package com.ljh.project.utils;

import com.ljh.project.entity.Userinfo;


public class TokenUtils {
    public static String getToken(Userinfo user) {
        String token="";
//        token= JWT.create()
//                .withKeyId(user.getId())
//                .withIssuer("www.ikertimes.com")
//                .withIssuedAt(new Date())
//                .withJWTId("jwt.ikertimes.com")
//                .withClaim("session_key", user.getSessionKey())
//                .withAudience(user.getId())
//                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }


}
