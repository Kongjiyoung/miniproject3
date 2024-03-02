package com.many.miniproject1.user;

import lombok.Data;

public class UserRequest {
    @Data
    public class SaveDTO {
        private Integer id;
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
    }
}