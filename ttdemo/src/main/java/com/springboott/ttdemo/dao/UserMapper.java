package com.springboott.ttdemo.dao;

import com.springboott.ttdemo.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Sam
 * @since 2019-09-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getUserByTj(User user);

}
