package com.many.miniproject1.offer;

import com.many.miniproject1.skill.Skill;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OfferResponse {

    // 04-01 YSH
    @Data
    public static class personOffersDTO {
        private Integer id;
        private Integer postId;
        private Integer resumeId;
        private Integer personId;
        private String profile;
        private String companyName;
        private String title;
        private Timestamp createdAt;

        @Builder
        public personOffersDTO(Offer offer) {
            this.id = offer.getPost().getId();
            this.postId = offer.getPost().getId();
            this.personId = offer.getResume().getUser().getId();
            this.profile = offer.getPost().getProfile();
            this.companyName = offer.getPost().getUser().getCompanyName();
            this.title = offer.getPost().getTitle();
            this.createdAt = offer.getCreatedAt();
        }
    }

    @Data
    public static class companyOffersDTO {
        private Integer offerId;
        private Integer companyId;
        private String profile;
        private String username;
        private String career;
        private String simpleIntroduce;
        private List<SkillDTO> skills = new ArrayList<>();

        @Data
        public class SkillDTO {
            private Integer id;
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
            this.career = offer.getResume().getCareer();
            this.simpleIntroduce = offer.getResume().getSimpleIntroduce();
            this.skills = offer.getResume().getSkills().stream().map(skill -> new SkillDTO(skill)).toList();
        }
    }

    @Data
    public static class personOfferDetailDTO {
        private Integer id;
        private Integer postId;
        private Integer personId;
        private String title;
        private String profile;
        private String career;
        private String pay;
        private String deadline;
        private String companyName;
        private String task;
        private String workStartTime;
        private String workEndTime;
        private String workingArea;
        private String workCondition;
        private List<SkillDTO> skills = new ArrayList<>();

        @Data
        public class SkillDTO {
            private Integer id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }

        }

        public personOfferDetailDTO(Offer offer) {
            this.id = offer.getId();
            this.postId = offer.getPost().getId();
            this.personId = offer.getResume().getId();
            this.title = offer.getPost().getTitle();
            this.profile = offer.getPost().getProfile();
            this.career = offer.getPost().getCareer();
            this.pay = offer.getPost().getPay();
            this.deadline = offer.getPost().getDeadline();
            this.companyName = offer.getPost().getUser().getCompanyName();
            this.task = offer.getPost().getTask();
            this.workStartTime = offer.getPost().getWorkStartTime();
            this.workEndTime = offer.getPost().getWorkEndTime();
            this.workingArea = offer.getPost().getWorkingArea();
            this.workCondition = offer.getPost().getWorkCondition();
            this.skills = offer.getPost().getSkillList().stream().map(skill -> new SkillDTO(skill)).toList();
        }
    }

    // 04-02 YSH
    @Data
    public static class companyOfferDetailDTO {
        private Integer offerId;
        private Integer resumeId;
        private Integer postId;
        private String title;
        private String profile;
        private String career;
        private String simpleIntroduce;
        private String portfolio;
        private String introduce;
        private String name;
        private String birth;
        private String tel;
        private String address;
        private String email;
        private List<SkillDTO> skills = new ArrayList<>();

        @Data
        public class SkillDTO {
            private Integer id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }

        public companyOfferDetailDTO(Offer offer) {
            this.offerId = offer.getId();
            this.resumeId = offer.getResume().getId();
            this.postId = offer.getPost().getId();
            this.title = offer.getResume().getTitle();
            this.profile = offer.getResume().getProfile();
            this.career = offer.getResume().getCareer();
            this.simpleIntroduce = offer.getResume().getSimpleIntroduce();
            this.portfolio = offer.getResume().getPortfolio();
            this.introduce = offer.getResume().getIntroduce();
            this.name = offer.getResume().getUser().getName();
            this.birth = offer.getResume().getUser().getBirth();
            this.tel = offer.getResume().getUser().getTel();
            this.address = offer.getResume().getUser().getAddress();
            this.email = offer.getResume().getUser().getEmail();
            this.skills = offer.getResume().getSkills().stream().map(skill -> new SkillDTO(skill)).toList();
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

    @Data
    public static class ChoiceDTO {
        private Integer resumeId;
        private Integer postId; // 채용공고 아이디

        public ChoiceDTO(Offer offer) {
            this.resumeId = offer.getResume().getId();
            this.postId = offer.getPost().getId();

        }
    }
}
