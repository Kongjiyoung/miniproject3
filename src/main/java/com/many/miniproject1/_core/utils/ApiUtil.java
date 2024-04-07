package com.many.miniproject1._core.utils;

import lombok.Data;

@Data
public class ApiUtil<T> {
    private Integer status; //200, 400, 401, 403, 404, 500
    private String msg;
    private T body1;

    //통신이 성공했을 때
    public ApiUtil(T body) {
        this.status = 200;
        this.msg = "성공";
        this.body1 = body;
    }

    //통신이 실패했을 때
    public ApiUtil(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.body1 = null;
    }
}