package com.simple.security.config.bean;

import com.pig4cloud.captcha.*;
import com.pig4cloud.captcha.base.Captcha;
import com.simple.exception.BadConfigurationException;
import lombok.Data;

import java.awt.*;
import java.util.Objects;

/**
 * 登陆属性配置
 *
 * @author simple
 * @date 2021/12/5
 */
@Data
public class LoginProperties {

    private LoginCode loginCode;

    /**
     * 验证码在redis中存储的key的前缀
     */
    private String captchaRedisPrefix;

    /**
     * 获取验证码生产类Captcha
     *
     * @return /
     */
    public Captcha getCaptcha() {
        if (Objects.isNull(loginCode)) {
            loginCode = new LoginCode();
            if (Objects.isNull(loginCode.getCodeType())) {
                loginCode.setCodeType(LoginCodeEnum.arithmetic);
            }
        }
        return switchCaptcha(loginCode);
    }

    /**
     * 根据配置生成验证码
     *
     * @param loginCode 验证码配置
     * @return /
     */
    public Captcha switchCaptcha(LoginCode loginCode) {
        Captcha captcha = null;
        synchronized (this) {
            switch (loginCode.getCodeType()) {
                case chinese_gif:
                    captcha = new ChineseGifCaptcha(loginCode.getWidth(), loginCode.getHeight(), loginCode.getLength());
                    break;
                case arithmetic:
                    captcha = new ArithmeticCaptcha(loginCode.getWidth(), loginCode.getHeight(), loginCode.getLength());
                    break;
                case chinese:
                    captcha = new ChineseCaptcha(loginCode.getWidth(), loginCode.getHeight(), loginCode.getLength());
                    break;
                case gif:
                    captcha = new GifCaptcha(loginCode.getWidth(), loginCode.getHeight(), loginCode.getLength());
                    break;
                case spec:
                    captcha = new SpecCaptcha(loginCode.getWidth(), loginCode.getHeight(), loginCode.getLength());
                    break;
                default:
                    throw new BadConfigurationException("验证码配置信息错误！正确配置信息请查看LoginCodeEnum");
            }
        }
        if (!loginCode.getFontName().isEmpty()) {
            captcha.setFont(new Font(loginCode.getFontName(), Font.PLAIN, loginCode.getFontSize()));
        }
        return captcha;
    }
}
