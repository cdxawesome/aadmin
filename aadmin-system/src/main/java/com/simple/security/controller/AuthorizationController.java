package com.simple.security.controller;

import com.pig4cloud.captcha.base.Captcha;
import com.simple.security.config.bean.LoginProperties;
import com.simple.utils.RedisUtils;
import com.simple.vo.CodeEnum;
import com.simple.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 登陆认证
 *
 * @author simple
 * @date 2021/12/5
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "系统：系统认证接口")
@RequiredArgsConstructor
public class AuthorizationController {

    private final RedisUtils redisUtils;

    @Resource
    private LoginProperties loginProperties;

    @ApiOperation("登陆认证")
    @PostMapping("/login")
    public R login() {
        return new R(CodeEnum.SUCCESS, "", null);
    }


    @GetMapping("/code")
    @ApiOperation("获取验证码")
    public R getCaptcha() {
        // 获取验证码生产类
        Captcha captcha = loginProperties.getCaptcha();
        // 获取uuid，作为验证码存储在redis中的key
        String uuid = loginProperties.getCaptchaRedisPrefix() + UUID.randomUUID();
        // 获取验证码的值
        String captchaValue = captcha.text();
        // 将验证码保存进redis中
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // 将验证码图片信息返回给前端
        Map<String, Object> imgResult = new HashMap<>();
        imgResult.put("img", captcha.toBase64());
        imgResult.put("uuid", uuid);
        return new R(CodeEnum.SUCCESS, "获取验证码成功", imgResult);
    }

}
