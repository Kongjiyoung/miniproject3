package com.many.miniproject1.user;

import lombok.Data;

public class UserRequest {
    @Data
    public static class JoinDTO{
        private String role;
        private String companyName;
        private String companyNum;
        private String username;
        private String email;
        private String password;
    }

    @Data
    public class SaveDTO {
        private Integer id;
    }

    @Data
    public static class UpdateDTO {
        private String email;
        private String password;
        private String username;
        private String tel;
        private String companyName;
        private String companyAddress;
        private String companyNum;
        private String companyYear;
        private String companyMemberNum;
    }

    @Data
    public static class LoginDTO {
        private String role;
        private String email;
        private String password;
    }
}