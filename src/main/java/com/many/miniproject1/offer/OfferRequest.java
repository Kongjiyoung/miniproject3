package com.many.miniproject1.offer;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

public class OfferRequest {
    @Data
    public class SaveDTO {
        private Integer id;
        private Integer resume_id;
        private Integer post_id;
        private Integer post_writer_id;
        private Integer resume_writer_id;
        private String title;
        private Date createdAt;
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
    }
}
