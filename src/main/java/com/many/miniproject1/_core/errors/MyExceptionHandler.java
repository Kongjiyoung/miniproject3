package com.many.miniproject1._core.errors;

import com.many.miniproject1._core.errors.exception.*;
import com.many.miniproject1._core.utils.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// RuntimeException이 터지면 해당 파일로 오류가 모인다
@RestControllerAdvice //데이터응답
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> ex400(Exception400 e){
        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage()); //http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST); //http body, http header
    }
    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> ex401(Exception401 e){
        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage()); //http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED); //http body, http header
    }
    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> ex403(RuntimeException e){
        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage()); //http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN); //http body, http header
    }
    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> ex404(RuntimeException e){
        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage()); //http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND); //http body, http header
    }
    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> ex500(RuntimeException e){
        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage()); //http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR); //http body, http header
    }

}