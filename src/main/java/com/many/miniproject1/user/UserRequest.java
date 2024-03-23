package com.many.miniproject1.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class UserRequest {

    @Data
    public static class PersonUpdateDTO{
        private String email;
        private String name;
        private String password;
        private String tel;
        private String address;
        private Date birth;
        private MultipartFile profile;
        public User toEntity(){
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
    public static class CompanyInfoUpdateDTO {
        private String profileBase64;
        private String address;
        private String tel;
        private String email;
        private String password;
    }

    @Data
    public static class LoginDTO{
        private String username;
        private String password;
    }

}