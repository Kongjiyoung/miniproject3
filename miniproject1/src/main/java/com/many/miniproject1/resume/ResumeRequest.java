package com.many.miniproject1.resume;

import lombok.Data;

public class ResumeRequest {

    @Data
    public class SaveDTO {
        private Integer id;
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
    }
}