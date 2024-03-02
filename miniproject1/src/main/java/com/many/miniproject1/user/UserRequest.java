package com.many.miniproject1.user;

import lombok.Data;

public class UserRequest {
    @Data
    public static class JoinDTO{
        private Integer id;
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
        private Integer id;
    }

    @Data
    public static class LoginDTO {
        private String email;
        private String password;
    }
}