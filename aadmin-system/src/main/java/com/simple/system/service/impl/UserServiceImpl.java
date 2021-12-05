package com.simple.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.system.domain.User;
import com.simple.system.mapper.UserMapper;
import com.simple.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author simple
 * @date 2021/12/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }
}
