package com.ljh.project.entity.result;

import lombok.Data;
import org.springframework.http.HttpStatus;

/***
 *Author zijing
 *Date 2023/4/19 9:25
 */
@Data
public class OkResult extends BaseResult{
    private Integer code;
    private String msg;
    private Object data;
    private String token;

    public OkResult() {
        this.code = HttpStatus.OK.value();
        this.msg = HttpStatus.OK.getReasonPhrase();
    }

    public OkResult(Object data){
        this.code = HttpStatus.OK.value();
        this.msg = HttpStatus.OK.getReasonPhrase();
        this.data = data;
    }
    public OkResult(String msg, Object data){
        this.code = HttpStatus.OK.value();
        this.msg = msg;
        this.data = data;
    }
}
