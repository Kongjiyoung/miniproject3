package com.many.miniproject1.apply;

import lombok.Data;


public class ApplyRequest {
    @Data
    public class SaveDTO {
        private Integer id;
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
    }
}