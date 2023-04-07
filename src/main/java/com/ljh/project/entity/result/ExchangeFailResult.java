package com.ljh.project.entity.result;

import lombok.Data;

/**
 * @author:hxy
 * @date 2022/1/18
 * @apiNote
 */

@Data
public class ExchangeFailResult extends BaseResult {
    private Integer code;
    private String msg;
    private Object data;
    public ExchangeFailResult() {
        this.code = 1000;
        this.msg = "成就点数不足，无法兑换";
    }

    public ExchangeFailResult(Object data){
        this.code = 1000;
        this.msg = "成就点数不足，无法兑换";
        this.data = data;
    }
    public ExchangeFailResult(String msg, Object data){
        this.code = 1000;
        this.msg = msg;
        this.data = data;
    }


}
