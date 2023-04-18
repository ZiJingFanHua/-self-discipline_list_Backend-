package com.ljh.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.project.entity.Userinfo;
import org.apache.ibatis.annotations.Param;

/***
 *Author zijing
 *Date 2023/4/18 15:46
 */
public interface UserinfoMapper extends BaseMapper<Userinfo> {

    public Userinfo getUserInfoById(String userId);

    public Long getAchievePointById(String userId);

    boolean daysAddOne(String id);

    boolean addPoints(@Param("id") String id, @Param("points") Integer points);
}
