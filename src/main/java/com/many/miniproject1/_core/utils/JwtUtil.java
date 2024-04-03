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
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60L * 60L))
                .withClaim("id", user.getId())
                .withClaim("role", user.getRole())
                .withClaim("username", user.getUsername())
                .withClaim("email", user.getEmail())
                .withClaim("password", user.getPassword())
                .withClaim("name", user.getName())
                .withClaim("tel", user.getTel())
                .withClaim("companyName", user.getCompanyName())
                .withClaim("address", user.getAddress())
                .withClaim("companyNum", user.getCompanyNum())
                .withClaim("profile", user.getProfile())
                .withClaim("profileName", user.getProfileName())
                .withClaim("birth", user.getBirth())
                .sign(Algorithm.HMAC512("many"));

        return jwt;
    }

    public static SessionUser verify(String jwt) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("many")).build().verify(jwt);
        Integer id = decodedJWT.getClaim("id").asInt();
        String username = decodedJWT.getClaim("username").asString();
        String role = decodedJWT.getClaim("role").asString();
        String email = decodedJWT.getClaim("email").asString();
        String password = decodedJWT.getClaim("password").asString();
        String name = decodedJWT.getClaim("name").asString();
        String tel = decodedJWT.getClaim("tel").asString();
        String companyName = decodedJWT.getClaim("companyName").asString();
        String address = decodedJWT.getClaim("address").asString();
        String companyNum = decodedJWT.getClaim("companyNum").asString();
        String profile = decodedJWT.getClaim("profile").asString();
        String profileName = decodedJWT.getClaim("profileName").asString();
        String birth = decodedJWT.getClaim("birth").asString();

        return SessionUser.builder()
                .id(id)
                .role(role)
                .username(username)
                .email(email)
                .password(password)
                .name(name)
                .tel(tel)
                .companyName(companyName)
                .address(address)
                .companyNum(companyNum)
                .profile(profile)
                .profileName(profileName)
                .birth(birth)
                .build();
    }
}
