package com.many.miniproject1.post;

import lombok.Data;

public class PostRequest {

    @Data
    public class SaveDTO {
        private Integer id;
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
    }
}