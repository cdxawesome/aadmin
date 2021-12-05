package com.simple.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.util.Date;


/**
 * @author simple
 * @date 2021/12/5
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("sys_user")
public class User extends BaseDomain {

    @Id
    private Long userId;

    private Long deptId;

    private String username;

    private String password;

    private String nickName;

    private String gender;

    private String phone;

    private String email;

    private String avatarPath;

    private Boolean isAdmin;

    private Boolean enabled;

    private Date pwdResetTime;
}
