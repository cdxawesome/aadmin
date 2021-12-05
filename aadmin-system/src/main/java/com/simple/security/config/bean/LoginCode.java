package com.simple.security.config.bean;

import lombok.Data;

/**
 * 登陆的验证码的配置信息
 *
 * @author simple
 * @date 2021年12月5日14:07:59
 */
@Data
public class LoginCode {

    /**
     * 验证码类型
     */
    private LoginCodeEnum codeType;

    /**
     * 验证码有效期(分钟)
     */
    private Long expiration = 2L;

    /**
     * 验证码内容长度
     */
    private int length = 2;

    /**
     * 验证码图片像素宽度
     */
    private int width = 112;

    /**
     * 验证码图片像素高度
     */
    private int height = 36;

    /**
     * 验证码字体名称
     */
    private String fontName;

    /**
     * 验证码字体大小
     */
    private int fontSize = 25;

}
