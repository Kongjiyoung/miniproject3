package com.many.miniproject1.user;

import lombok.Data;

public class UserRequest {
    @Data
    public static class JoinDTO{
        private String role;
        private String email;
        private String password;
        private String username;
        private String companyName;
        private String companyNum;
        private String address;
        private String birth;
        private String tel;
    }

    @Data
    public class SaveDTO {
        private Integer id;
    }

    @Data
    public static class CompanyUpdateDTO {
        private Integer id;
        private String profile;
        private String companyName;
        private String companyNum;
        private String address;
        private String email;
        private String password;
        private String username;
        private String tel;


    }
    @Data
    public static class PersonUpdateDTO {
        private String profile;
        private String username;
        private String address;
        private String birth;
        private String tel;
        private String email;
        private String password;

    }

    @Data
    public static class LoginDTO {
        private String role;
        private String email;
        private String password;
    }

    @Data
    public static class PasswordChangeDTO{
        private String password;

    }
}