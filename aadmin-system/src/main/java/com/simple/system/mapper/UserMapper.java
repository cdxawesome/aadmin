package com.simple.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simple.system.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author simple
 * @date 2021/12/5
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
