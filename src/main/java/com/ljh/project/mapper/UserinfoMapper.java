package com.ljh.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljh.project.entity.Userinfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 木心
 * @since 2022-01-16
 */
public interface UserinfoMapper extends BaseMapper<Userinfo> {

    public Userinfo getUserInfoById(String userId);

    public Long getAchievePointById(String userId);

    boolean daysAddOne(String id);

    boolean addPoints(@Param("id") String id, @Param("points") Integer points);
}
