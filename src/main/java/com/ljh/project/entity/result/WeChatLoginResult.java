package com.ljh.project.entity.result;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WeChatLoginResult extends BaseResult {
    private String errmsg;
    private String openid;
    private String session_key;
    private Integer errcode;
    private String unionid;
}
