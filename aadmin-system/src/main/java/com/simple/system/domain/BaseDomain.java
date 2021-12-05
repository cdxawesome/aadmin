package com.simple.system.domain;

import lombok.*;

import java.util.Date;

/**
 * @author simple
 * @date 2021/12/5
 */
@Data
public class BaseDomain {
    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;
}
