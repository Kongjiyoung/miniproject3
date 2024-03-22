package com.many.miniproject1.scrap;

import lombok.Data;

import java.sql.Timestamp;

public class ScrapRequest {
    @Data
    public static class SaveResumeDTO {
        private Integer resumeId;
        private Integer companyId;
        private Timestamp createdAt;
    }
    @Data
    public static class SavePostDTO {
        private Integer postId;
        private Integer personId;
        private Timestamp createdAt;
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
    }
}