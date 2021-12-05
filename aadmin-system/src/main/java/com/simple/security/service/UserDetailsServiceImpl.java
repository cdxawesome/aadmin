package com.simple.security.service;

import com.simple.system.domain.Role;
import com.simple.system.domain.User;
import com.simple.system.service.RoleService;
import com.simple.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author simple
 * @date 2021/12/5
 */
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        // 校验用户名
        try {
            user = userService.findByUsername(username);
        } catch (Exception e) {
            // SpringSecurity会自动转换UsernameNotFoundException为BadCredentialsException
            throw new UsernameNotFoundException("", e);
        }
        if (!user.getEnabled()) {
            throw new RuntimeException("账号未激活");
        }

        List<Role> roles = roleService.findByUserId(user.getUserId());
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return new
                org.springframework.security.core.userdetails.User
                (username, user.getPassword(), authorities);
    }
}
