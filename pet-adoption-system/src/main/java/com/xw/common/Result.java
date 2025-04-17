package com.xw.common;


import lombok.Data;

@Data
public class Result<T> {
    // 状态码
    private String code;
    // 提示消息
    private String msg;
    // 接口返回内容
    private T data;

    public Result(T data,Constants constants) {
        this.code = constants.getCode();
        this.msg = constants.getMsg();
        this.data = data;
    }

    public Result(Constants constants) {
        this.code = constants.getCode();
        this.msg = constants.getMsg();
    }

    public Result(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Constants constants,String msg) {
        this.code = constants.getCode();
        this.msg = msg;
    }
}
