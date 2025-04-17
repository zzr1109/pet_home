package com.xw.common;

import lombok.Getter;

@Getter
public enum Constants {
    SUCCESS("200","success"),
    ERROR("500","error"),
    PARAMETER_ERROR("400",""),
    UNAUTHORIZED("401","")
    ;

    private String code;
    private String msg;

    Constants(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
