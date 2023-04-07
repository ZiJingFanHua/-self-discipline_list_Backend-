package com.ljh.project.entity.result;

import com.ljh.project.entity.param.TaskParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author:hxy
 * @date 2022/4/22
 * @apiNote
 * 返回两个列表，一个是已经完成的task，一个是未完成的task
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCompletionResult extends BaseResult {

    //已完成任务列表
    private List<TaskParam> completedTaskList;
    //未完成任务列表
    private List<TaskParam> unCompletedTaskList;
    private String msg;
    private Integer code;

    public TaskCompletionResult(List<TaskParam> completed,List<TaskParam> unCompleted){
        this.completedTaskList = completed;
        this.unCompletedTaskList = unCompleted;
        this.code = HttpStatus.OK.value();
        this.msg = HttpStatus.OK.getReasonPhrase();
    }
}
