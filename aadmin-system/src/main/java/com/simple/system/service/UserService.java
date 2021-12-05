package com.simple.system.service;

import com.simple.system.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author simple
 * @date 2021/12/5
 */
public interface UserService {

    public User findByUsername(String username);

}
