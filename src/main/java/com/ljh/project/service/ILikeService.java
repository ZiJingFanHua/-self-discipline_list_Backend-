package com.ljh.project.service;

import com.ljh.project.entity.Like;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zijing
 * @since 2023-05-10
 */
/***
 *Author zijing
 *Date 2023/5/10 11:16
 */
public interface ILikeService extends IService<Like> {

    public boolean addLike(Like like);

    public boolean droplike(Like like);
}
