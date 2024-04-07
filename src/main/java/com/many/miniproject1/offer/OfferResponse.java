package com.many.miniproject1.offer;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.skill.Skill;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class OfferResponse {
    @Data
    public static class PersonOffersDTO {
        private Integer id;
        private String title;
        private String career;
        private String pay;
        private String workCondition;
        private String workStartTime;
        private String workEndTime;
        private String deadline;
        private String task;
        private String profile;
        private String profileName;
        private String workingArea;
        private List<SkillDTO> skllList;

        @Builder
        public PersonOffersDTO(Offer offer, Post post, List<Skill> skllList) {
            this.id = offer.getId();
            this.title = post.getTitle();
            this.career = post.getCareer();
            this.pay = post.getPay();
            this.workCondition = post.getWorkCondition();
            this.workStartTime = post.getWorkStartTime();
            this.workEndTime = post.getWorkEndTime();
            this.deadline = post.getDeadline();
            this.task = post.getTask();
            this.profile = post.getProfile();
            this.profileName = post.getProfileName();
            this.workingArea = post.getWorkingArea();
            this.skllList = skllList.stream().map(SkillDTO::new).toList();
        }

        @Data
        public class SkillDTO {
            private Integer id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }
    }

    @Data
    public static class PersonOfferDetailDTO {
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
        private List<SkillDTO> skills;

        @Data
        public class SkillDTO {
            private Integer id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }

        }

        public PersonOfferDetailDTO(Offer offer) {
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
            this.skills = offer.getPost().getSkillList().stream().map(SkillDTO::new).toList();
        }
    }

    @Data
    public static class CompanyOffersDTO {
        private Integer offerId;
        private Integer companyId;
        private String profile;
        private String username;
        private String career;
        private String simpleIntroduce;
        private List<SkillDTO> skills;

        @Data
        public class SkillDTO {
            private Integer id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }

        public CompanyOffersDTO(Offer offer) {
            this.offerId = offer.getId();
            this.companyId = offer.getPost().getUser().getId();
            this.profile = offer.getResume().getProfile();
            this.username = offer.getResume().getUser().getName();
            this.career = offer.getResume().getCareer();
            this.simpleIntroduce = offer.getResume().getSimpleIntroduce();
            this.skills = offer.getResume().getSkills().stream().map(SkillDTO::new).toList();
        }
    }

    @Data
    public static class CompanyOfferDetailDTO {
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
        private List<SkillDTO> skills;

        @Data
        public class SkillDTO {
            private Integer id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }

        public CompanyOfferDetailDTO(Offer offer) {
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
            this.skills = offer.getResume().getSkills().stream().map(SkillDTO::new).toList();
        }
    }

    @Data
    public static class OfferDTO {
        private Integer id;
        private Integer resumeId;
        private Integer postId;

        public OfferDTO(Offer offer) {
            this.id = offer.getId();
            this.resumeId = offer.getResume().getId();
            this.postId = offer.getPost().getId();
        }
    }
}
