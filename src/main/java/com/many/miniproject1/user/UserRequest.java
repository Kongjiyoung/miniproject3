package com.many.miniproject1.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class UserRequest {

    @Data
    public static class PersonUpdateDTO {
        private String email;
        private String name;
        private String password;
        private String tel;
        private String address;
        private Date birth;
        private MultipartFile profile;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .name(name)
                    .password(password)
                    .tel(tel)
                    .address(address)
                    .birth(birth)
                    .profile(profile.getOriginalFilename())
                    .build();
        }
    }

    @Data
    public static class PersonJoinDTO {
        private String role;
        private MultipartFile profile;
        private String username;
        private String name;
        private String email;
        private String birth;
        private String tel;
        private String address;
        private String password;

        public User toEntity() {
            String profileImagePath = ProfileImageService.saveProfile(profile);
            return User.builder()
                    .role(role)
                    .profile(profileImagePath)
                    .username(username)
                    .name(name)
                    .email(email)
                    .birth(Date.valueOf(birth))
                    .tel(tel)
                    .address(address)
                    .password(password)
                    .build();
        }
    }

    @Data
    public static class CompanyInfoUpdateDTO {
        private Integer id;
        private MultipartFile profile;
        private String address;
        private String tel;
        private String email;
        private String password;
        private String newPassword;
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

}