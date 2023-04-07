package com.ljh.project.entity.result;

import lombok.Data;

/**
 * @author:hxy
 * @date 2022/1/18
 * @apiNote
 */

@Data
public class NoFindingErrResult extends BaseResult {
    private Integer code;
    private String msg;
    private Object data;
    public NoFindingErrResult() {
        this.code = 1001;
        this.msg = "该奖励不存在";
    }

    public NoFindingErrResult(Object data){
        this.code = 1001;
        this.msg = "该奖励不存在";
        this.data = data;
    }
    public NoFindingErrResult(String msg, Object data){
        this.code = 1001;
        this.msg = msg;
        this.data = data;
    }


}
