package com.many.miniproject1.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class UpdateDTO {
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