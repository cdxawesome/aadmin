package com.simple.security.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author simple
 * @date 2021/12/5
 */
@Data
public class AuthUserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotEmpty
    private String code;

    @NotEmpty
    private String uuid;
}
