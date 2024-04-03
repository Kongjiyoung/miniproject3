package com.many.miniproject1;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.many.miniproject1._core.utils.JwtUtil;
import com.many.miniproject1.user.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class JwtUtilTest {

    @Test
    public void create_test(){
        // given
        User user = User.builder()
                .id(6)
                .username("ssar")
                .build();

        // when
        String jwt = JWT.create()
                .withSubject("blog")
                .withExpiresAt(new Date(System.currentTimeMillis()+ 1000*10))
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .sign(Algorithm.HMAC512("metacoding"));
        System.out.println(jwt);
    }

    @Test
    public void verify_test(){
        // given
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJibG9nIiwiaWQiOjEsImV4cCI6MTcxMjEyNDY0MSwidXNlcm5hbWUiOiJzc2FyIn0.D4oxR0YGVSQ9MLAnfe_3Vunna35bPIZWzaI5bKXsFdZLOQT94EElZsuScs-Ow0MJWu4vqatWxbMbFdfeO4TkiQ";

        // when
        JwtUtil.verify(jwt);

        // then
    }
}