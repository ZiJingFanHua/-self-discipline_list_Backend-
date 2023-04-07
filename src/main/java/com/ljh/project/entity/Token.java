package com.ljh.project.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Token {
    private String openid;
    private String session_key;
    private String token;

    public Token() {

    }

    public String getToken() {
        token = "openid=" + openid + "&session_key=" + session_key;
        return token;
    }

    public  Token(String token) {
        String[] split = token.split("&");

        String[] openid = split[0].split("=");
        this.openid = openid[1];
        String[] session_key = split[1].split("=");
        this.session_key = session_key[1];
        this.token = token;
    }
}
