package com.ljh.project.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WeChatLoginParam {
    private String js_code;
    private String secret = "748b254c5e2db673486eb86f772ffa0d";
    private String appid = "wxbc17cbc98259e7c7";
    private String grant_type = "authorization_code";

    public String getUrl() {
        return "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + js_code + "&grant_type=" + grant_type;
    }
}
