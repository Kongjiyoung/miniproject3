package com.many.miniproject1._core.errors;

import com.many.miniproject1._core.errors.exception.*;
import com.many.miniproject1._core.utils.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> ex400(Exception400 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.warn("400 : " + e.getMessage());
        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage()); //http body -> 구성한 객체

        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST); //http body, http header 배포 전 단계에서 사용
//        return "err/400"; // 배포 완료 후 사용
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> ex401(Exception401 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.warn("401 : " + e.getMessage()); // ex) 로그인 실패 다이렉트 메세지 [위험도는 낮지만 주의해야 하는 점이 있다 : 강제로 접속하는 인원이 발생]
        log.warn("IP : " + request.getRemoteAddr()); // 누군지 IP 확인
        log.warn("Agent : " + request.getHeader("User-Agent")); // 장비 확인
        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage());

        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED);
//        return "err/401"; // 배포 완료 후 사용

    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> ex403(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.warn("403 : " + e.getMessage());
        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage());

        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN);
//        return "err/403"; // 배포 완료 후 사용
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> ex404(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.info("404 : " + e.getMessage());
        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage());

        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND);
//        return "err/404"; // 배포 완료 후 사용
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> ex500(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.error("500 : " + e.getMessage());
        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage());

        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
//        return "err/500"; // 배포 완료 후 사용
    }
}