package com.many.miniproject1.main;

import lombok.Data;

import java.sql.Timestamp;

public class MainResponse {
    @Data
    public class resumeDTO {
        private Integer id;
        private Integer personId;
        private String title;
        private String profile;
        private String username;
        private String birth;
        private String tel;
        private String address;
        private String email;
        private String portfolio;
        private String introduce;
        private Timestamp created_at;
    }

}
