package com.many.miniproject1._core.utils;

import lombok.Data;

@Data
public class ApiUtil<T> {
    private Integer status ; //200,400,404,405
    private String msg ; // 성공, 실패 -> 메세지  성공시에는 메세지 필요없음
    private T body1 ; // 타입이 정해져있지 않아 new 때 타입을 정하는 T로 정함

    //통신이 성공했을 때
    public ApiUtil(T body) {
        this.status = 200;
        this.msg ="성공";
        this.body1 = body;
    }

    //통신이 실패했을 때
    public ApiUtil(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.body1=null;  // 실패 시에는 메세지와 상태코드만 필요하기 때문에 바디 데이터는 필요없다.
    }
}