package com.alan.java.debug.sample.pojo;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result() {
        this.code = 0;
        this.msg = "ok";
    }

    public Result(T data) {
        this();
        this.data = data;
    }
}
