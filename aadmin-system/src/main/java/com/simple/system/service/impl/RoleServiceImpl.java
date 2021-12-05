package com.simple.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.system.domain.Role;
import com.simple.system.mapper.RoleMapper;
import com.simple.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author simple
 * @date 2021/12/5
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role findById(Long id) {
        return null;
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return new ArrayList<>(roleMapper.selectList(null));
    }
}
