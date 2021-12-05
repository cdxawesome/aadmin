package com.simple.test1;

import com.simple.system.domain.Role;
import com.simple.system.domain.User;
import com.simple.system.service.RoleService;
import com.simple.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

/**
 * @author simple
 * @date 2021/12/5
 */
@SpringBootTest
public class TestService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Test
    public void TestUserService(){
        User admin = userService.findByUsername("admin");
        String createBy = admin.getUpdateBy();
        System.out.println(createBy);
        System.out.println(admin);
    }

    @Test
    public void testRoleService(){
        List<Role> all = roleService.findAll();
        System.out.println(all);
    }
}
