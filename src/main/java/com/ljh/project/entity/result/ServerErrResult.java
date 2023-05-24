package com.ljh.project.entity.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/***
 *Author zijing
 *Date 2023/4/19 9:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerErrResult extends BaseResult{
    private Integer code;
    private String msg;
    private Object data;
    private String token;

    public ServerErrResult(String msg){
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.msg = msg;
    }

    public ServerErrResult(String msg, Object data){
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.msg = msg;
        this.data = data;
    }
}
