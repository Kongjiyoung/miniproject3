package com.many.miniproject1._core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.many.miniproject1.user.SessionUser;
import com.many.miniproject1.user.User;

import java.util.Date;

public class JwtUtil {

    public static String create(User user) {

        String jwt = JWT.create()
                .withSubject("miniproject")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L * 365L))
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("role", user.getRole())
                .sign(Algorithm.HMAC512("many"));
        return jwt;
    }

    public static SessionUser verify(String jwt) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("many")).build().verify(jwt);
        Integer id = decodedJWT.getClaim("id").asInt();
        String username = decodedJWT.getClaim("username").asString();
        String role = decodedJWT.getClaim("role").asString();

        return SessionUser.builder()
                .id(id)
                .role(role)
                .username(username)
                .build();
    }
}
