package com.simple.system.service;

import com.simple.system.domain.Role;

import java.util.List;
import java.util.Set;

/**
 * @author simple
 * @date 2021/12/5
 */
public interface RoleService {
    public Role findById(Long id);

    public List<Role> findByUserId(Long userId);

    public List<Role> findAll();
}
