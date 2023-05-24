package com.ljh.project.controller;


import com.ljh.project.entity.Like;
import com.ljh.project.entity.param.LikeParam;
import com.ljh.project.entity.param.PointArchiveParam;
import com.ljh.project.entity.result.BaseResult;
import com.ljh.project.entity.result.OkResult;
import com.ljh.project.service.impl.LikeServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zijing
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    LikeServiceImpl likeService;
    @PostMapping("/addLike")
    @ApiOperation(value = "添加点赞")
    public BaseResult addLike(@RequestBody Like like){
        boolean b = likeService.addLike(like);
        return new OkResult("添加成功",null);
    }

    @PostMapping("/deleteLike")
    @ApiOperation(value = "取消点赞")
    public BaseResult deleteLike(@RequestBody Like like){
        boolean b = likeService.droplike(like);
        return new OkResult("删除成功",null);
    }
}
