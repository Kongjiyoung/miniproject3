package com.many.miniproject1.offer;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.skill.Skill;
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
        int offerId;
        int personId;
        String profile;
        String companyName;
        String title;
        Timestamp createdAt;

        @Builder
        public personOffersDTO(Offer offer, Post post, User user) {
            this.offerId = post.getId();
            this.personId = offer.getResume().getUser().getId();
            this.profile = post.getProfile();
            this.companyName = user.getCompanyName();
            this.title = post.getTitle();
            this.createdAt = offer.getCreatedAt();
        }
    }

    @Data
    public static class companyOffersDTO {
        int offerId;
        int companyId;
        String profile;
        String username;
        String carrer;
        String simpleIntroduce;
        List<SkillDTO> skills = new ArrayList<>();

        @Data
        public class SkillDTO{
            private int id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }

        public companyOffersDTO(Offer offer) {
            this.offerId = offer.getId();
            this.companyId = offer.getPost().getUser().getId();
            this.profile = offer.getResume().getProfile();
            this.username = offer.getResume().getUser().getName();
            this.carrer = offer.getResume().getCareer();
            this.simpleIntroduce = offer.getResume().getSimpleIntroduce();
            this.skills = offer.getResume().getSkillList().stream().map(skill -> new SkillDTO(skill)).toList();
        }
    }

    @Data
    public static class personOfferDetailDTO{
        int postId;
        int personId;
        String title;
        String profile;
        String career;
        String pay;
        String deadline;
        String companyName;
        String task;
        String workStatTime;
        String workEndTime;
        String workingArea;
        String workCondition;
        List<SkillDTO> skills = new ArrayList<>();

        @Data
        public class SkillDTO{
            private int id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }

        }

        public personOfferDetailDTO(Offer offer) {
            this.postId = offer.getPost().getId();
            this.personId = offer.getResume().getId();
            this.title = offer.getPost().getTitle();
            this.profile = offer.getPost().getProfile();
            this.career = offer.getPost().getCareer();
            this.pay = offer.getPost().getPay();
            this.deadline = offer.getPost().getDeadline();
            this.companyName = offer.getPost().getUser().getCompanyName();
            this.task = offer.getPost().getTask();
            this.workStatTime = offer.getPost().getWorkStartTime();
            this.workEndTime = offer.getPost().getWorkEndTime();
            this.workingArea = offer.getPost().getWorkingArea();
            this.workCondition = offer.getPost().getWorkCondition();
            this.skills = offer.getPost().getSkillList().stream().map(skill -> new SkillDTO(skill)).toList();
        }
    }
    // 04-01 YSH


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
