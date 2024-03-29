package com.many.miniproject1.offer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OfferResponse {

    @Data
    public static class OfferDTO {
        private Integer id;
        private Integer resumeId;
        private Integer postId;
        private Integer companyId;
        private Integer personId;
    }

    @Data
    public static class OfferedResumeDetailDTO {
        private Integer id;
        private Integer userId;
        private String title;
        private String profile;
        private String name;
        private String birth;
        private String tel;
        private String address;
        private String email;
        private String career;
        private String simpleIntroduce;
        private String portfolio;
        private List<OfferedResumeSkillDTO> skills = new ArrayList<>();
        private String introduce;

        public static class OfferedResumeSkillDTO {
            private Integer id;
            private String skill;
            private int resumeId;
        }
    }
}
