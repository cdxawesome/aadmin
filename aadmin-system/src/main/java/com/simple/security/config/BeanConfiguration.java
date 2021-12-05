package com.simple.security.config;

import com.simple.security.config.bean.LoginProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件yml转pojo类的统一配置类
 *
 * @author simple
 * @date 2021/12/5
 */
@Configuration
public class BeanConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "login")
    public LoginProperties loginProperties() {
        return new LoginProperties();
    }
}
