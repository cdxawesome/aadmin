package com.simple.security.controller;

import com.pig4cloud.captcha.base.Captcha;
import com.simple.exception.BadConfigurationException;
import com.simple.security.config.bean.LoginProperties;
import com.simple.security.service.dto.AuthUserDto;
import com.simple.utils.RedisUtils;
import com.simple.utils.RsaUtils;
import com.simple.vo.CodeEnum;
import com.simple.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    Logger logger = LoggerFactory.getLogger("MyLogger");

    private final RedisUtils redisUtils;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Value("${rsa.private-key}")
    private String rsaPrivateKey;

    @Resource
    private LoginProperties loginProperties;

    @ApiOperation("登陆认证")
    @PostMapping("/login")
    public R login(@RequestBody @Validated AuthUserDto authUser) throws Exception {
        // 密码解密(前端传回的密码是通过rsa加密的，我们需要解密得到原文才能做密码比较)
        String rawPassword = RsaUtils.decryptByPrivateKey(rsaPrivateKey, authUser.getPassword());
        // 从redis中查询验证码的值
        String captchaValue = (String) redisUtils.get(authUser.getUuid());
        // 校验验证码
        if (StringUtils.isEmpty(captchaValue)) {
            logger.error("验证码不存在或者已过期");
        }
        if (StringUtils.isEmpty(authUser.getCode()) || !captchaValue.equals(authUser.getCode())) {
            return new R(CodeEnum.FAIL, "验证码错误", null);
        }
        // 对用户进行系统认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), rawPassword);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 生成token

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
