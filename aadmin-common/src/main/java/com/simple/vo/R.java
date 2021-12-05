package com.simple.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义结果类
 *
 * @author simple
 * @date 2021/12/5
 */
@Data
@AllArgsConstructor
public class R {
    /**
     * 响应状态码
     */
    private CodeEnum code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应数据
     */
    private Object data;
}
