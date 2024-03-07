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
    public static class OfferBoardDTO{

        private Integer id;
        private String companyName;
        private Integer postId;
        private String title;
        private Timestamp createdAt;
        private String profile;
    }

    // resume_tb 전체 값과, user_tb.username담을 DTO
    @Data
    public static class OfferResumeDTO{
        // resume_tb
        private Integer id;
        private Integer personId; // resumeWriterId
        private String title;
        private String profile;
        private String portfolio;
        private String introduce;
        private String career;
        private String simpleIntroduce;
        private Timestamp createdAt;
        // user_tb
        private String username;
    }

    @Data
    public static class companyFindAllOfferDTO{
        private Integer id;

    }
}
