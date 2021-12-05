package com.simple.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * @author simple
 * @date 2021/12/5
 */
@EqualsAndHashCode(callSuper = false)
@Data
@ToString
@TableName("sys_role")
public class Role extends BaseDomain {
    @Id
    private Long roleId;
    private String name;
    private int level;
    private String description;
}
