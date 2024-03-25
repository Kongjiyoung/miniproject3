package com.many.miniproject1.resume;

import com.many.miniproject1.skill.Skill;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class ResumeResponse {
    //    @Data
//    public static class resumeDTO {
//        private Integer id;
//        private Integer personId;
//        private String title;
//        private String profile;
//        private String portfolio;
//        private String introduce;
//        private String career;
//        private String simpleIntroduce;
//        private String email;
//        private String password;
//        private String username;
//        private String tel;
//        private String address;
//        private Timestamp createdAt;
//        private List<String> skill;
//
//        public resumeDTO(ResumeRequest.ResumeDTO, List<String> skill) {
//            this.id = resume.getId();
//            this.personId = resume.getPersonId();
//            this.title = resume.getTitle();
//            this.profile = resume.getProfile();
//            this.portfolio = resume.getPortfolio();
//            this.introduce = resume.getIntroduce();
//            this.career = resume.getCareer();
//            this.simpleIntroduce = resume.getSimpleIntroduce();
//            this.createdAt = resume.getCreatedAt();
//
//            this.skill=skill;
//        }
//    }

    @Data
    public static class DetailSkillDTO {
        private Integer id;
        private Integer personId;
        private String title;
        private String profile;
        private String portfolio;
        private String introduce;
        private String career;
        private String simpleIntroduce;
        private List<String> skill;
        private Timestamp createdAt;
        private String email;
        private String username;
        private String tel;
        private String address;
        private String birth;

        public DetailSkillDTO(DetailDTO resume, List<String> skill) {
            this.id = resume.getId();
            this.personId = resume.getPersonId();
            this.title = resume.getTitle();
            this.profile = resume.getProfile();
            this.portfolio = resume.getPortfolio();
            this.introduce = resume.getIntroduce();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.createdAt = resume.getCreatedAt();
            this.email = resume.getEmail();
            this.username = resume.getUsername();
            this.tel = resume.getTel();
            this.address = resume.getAddress();
            this.birth = resume.getBirth();
            this.skill = skill;
        }

        public DetailSkillDTO(DetailDTO resume, Optional<Skill> skills) {

        }
    }

    @Data
    public static class DetailDTO {
        private Integer id;
        private Integer personId;
        private String title;
        private String profile;
        private String portfolio;
        private String introduce;
        private String career;
        private String simpleIntroduce;
        private Timestamp createdAt;
        private String email;
        private String username;
        private String tel;
        private String address;
        private String birth;
    }

    @Data
    public static class skillDTO {
        private Integer resumeId;
        private String skill;
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
        private Integer personId;
        private String title;
        private String profile;
        private String portfolio;
        private String introduce;
        private String career;
        private String simpleIntroduce;
        private List<String> skills;
        private Timestamp createdAt;
    }
}
