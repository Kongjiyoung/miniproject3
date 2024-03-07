package com.many.miniproject1.offer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

public class OfferResponse {

    @Data
    public static class OfferDTO{
        private Integer id;
        private Integer resumeId;
        private Integer postId;
        private Integer companyId;
        private Integer personId;
    }

    @Data
    @AllArgsConstructor
    public static class OfferBoardDTO{

        private Integer id;
        private String companyName;
        private Integer postId;
        private Timestamp createdAt;
        private String title;

        public OfferBoardDTO() {

        }
    }

    @Data
    public static class companyFindAllOfferDTO{
        private Integer id;

    }
}
