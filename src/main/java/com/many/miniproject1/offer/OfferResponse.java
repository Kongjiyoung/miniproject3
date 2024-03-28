package com.many.miniproject1.offer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class OfferResponse {

    @Data
    public static class OfferDTO{
        private Integer id;
        private Integer resumeId;
        private Integer postId;
        private Integer companyId;
        private Integer personId;
    }
}
