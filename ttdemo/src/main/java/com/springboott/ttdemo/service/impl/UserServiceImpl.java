package com.springboott.ttdemo.service.impl;

import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.dao.UserMapper;
import com.springboott.ttdemo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Sam
 * @since 2019-09-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
