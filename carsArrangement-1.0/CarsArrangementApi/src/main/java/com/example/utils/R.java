package com.example.utils;


import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 */
@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code; //

    private String msg; //错误信息
    private T data; //数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = ResultCode.SUCCESS;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = ResultCode.ERROR;
        return r;
    }
}
