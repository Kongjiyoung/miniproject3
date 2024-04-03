package com.many.miniproject1._core.interceptor;

import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1._core.utils.JwtUtil;
import com.many.miniproject1.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Bearer jwt 토큰이 들어옴
        String jwt = request.getHeader("Authorization");

        if (jwt == null) {
            throw new Exception401("jwt 토큰을 전달해주세요");
        }

        jwt = jwt.replace("Bearer ", "");

        //검증
        try {
            SessionUser sessionUser = JwtUtil.verify(jwt);

            // 임시 세션 (jsessionId는 필요 없음)
            HttpSession session = request.getSession();
            session.setAttribute("sessionUser", sessionUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
