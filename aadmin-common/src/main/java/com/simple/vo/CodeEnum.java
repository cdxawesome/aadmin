package com.simple.vo;

/**
 * @author simple
 * @date 2021/12/5
 */
public enum CodeEnum {
    SUCCESS(10000),
    FAIL(10001);

    public final int code;

    CodeEnum(int code) {
        this.code = code;
    }
}
