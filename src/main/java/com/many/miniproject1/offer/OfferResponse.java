package com.many.miniproject1.offer;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OfferResponse {

    // 04-01 YSH
    @Data
    public static class personOffersDTO{
        int id;
        String profile;
        String companyName;
        String title;
        Timestamp createdAt;

        @Builder
        public personOffersDTO(Offer offer, Post post, User user) {
            this.id = post.getId();
            this.profile = post.getProfile();
            this.companyName = user.getUsername();
            this.title = post.getTitle();
            this.createdAt = offer.getCreatedAt();
        }
    }

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
