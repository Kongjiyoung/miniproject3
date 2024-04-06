package com.many.miniproject1._core.errors;

import com.many.miniproject1._core.errors.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
// RuntimeException이 터지면 해당 파일로 오류가 모인다
@RestControllerAdvice //데이터응답
public class MyExceptionHandler {

    // TODO: TEST 를 위한 코드를 배포를 위해 log 로 변경 [전체 수정]

    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.warn("400 : " + e.getMessage());
        return "err/400";
//        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage()); //http body -> 구성한 객체
//        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST); //http body, http header
    }

    @ExceptionHandler(Exception401.class)
    public String ex401(Exception401 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.warn("401 : " + e.getMessage()); // ex) 로그인 실패 다이렉트 메세지 [위험도는 낮지만 주의해야 하는 점이 있다 : 강제로 접속하는 인원이 발생]
        log.warn("IP : " + request.getRemoteAddr()); // 누군지 IP 확인
        log.warn("Agent : " + request.getHeader("User-Agent")); // 장비 확인
        return "err/401";
//        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage()); //http body -> 구성한 객체
//        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED); //http body, http header
    }

    @ExceptionHandler(Exception403.class)
    public String ex403(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.warn("403 : " + e.getMessage());
        return "err/403";
//        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage()); //http body -> 구성한 객체
//        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN); //http body, http header
    }

    @ExceptionHandler(Exception404.class)
    public String ex404(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.info("403 : " + e.getMessage());
        return "err/404";
//        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage()); //http body -> 구성한 객체
//        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND); //http body, http header
    }

    @ExceptionHandler(Exception500.class)
    public String ex500(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.error("500 : " + e.getMessage());
        return "err/500";
//        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage()); //http body -> 구성한 객체
//        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR); //http body, http header
    }

}