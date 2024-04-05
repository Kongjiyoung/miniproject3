package com.many.miniproject1.resume;

import com.many.miniproject1.skill.Skill;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class ResumeResponse {

    // 04-02 YSH
    @Data
    public static class SaveResumeDTO {
        private Integer resumeId;
        private String title;
        private String profile;
        private String profileName;
        private String career;
        private String simpleIntroduce;
        private String portfolio;
        private String introduce;
        private List<SkillDTO> skills;

        public SaveResumeDTO(Resume resume, List<Skill> skills) {
            this.resumeId = resume.getId();
            this.title = resume.getTitle();
            this.profile = resume.getProfile();
            this.profileName = resume.getProfileName();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.portfolio = resume.getPortfolio();
            this.introduce = resume.getIntroduce();
            this.skills = skills.stream().map(SkillDTO::new).toList();
        }

        @Data
        public static class SkillDTO {
            private Integer id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }
    }

//    @Data
//    public static class DetailSkillDTO {
//        private Integer id;
//        private Integer personId;
//        private String title;
//        private String profile;
//        private String portfolio;
//        private String introduce;
//        private String career;
//        private String simpleIntroduce;
//        private List<String> skill;
//        private Timestamp createdAt;
//        private String email;
//        private String username;
//        private String tel;
//        private String address;
//        private String birth;
//
//        public DetailSkillDTO(DetailDTO resume, List<String> skill) {
//            this.id = resume.getId();
//            this.personId = resume.getPersonId();
//            this.title = resume.getTitle();
//            this.profile = resume.getProfile();
//            this.portfolio = resume.getPortfolio();
//            this.introduce = resume.getIntroduce();
//            this.career = resume.getCareer();
//            this.simpleIntroduce = resume.getSimpleIntroduce();
//            this.createdAt = resume.getCreatedAt();
//            this.email = resume.getEmail();
//            this.username = resume.getUsername();
//            this.tel = resume.getTel();
//            this.address = resume.getAddress();
//            this.birth = resume.getBirth();
//            this.skill = skill;
//        }
//
//        public DetailSkillDTO(Resume resume, List<Skill> skills) {
//
//        }
//    }

//    @Data
//    public static class DetailDTO {
//        private Integer id;
//        private Integer personId;
//        private String title;
//        private String profile;
//        private String portfolio;
//        private String introduce;
//        private String career;
//        private String simpleIntroduce;
//        private Timestamp createdAt;
//        private String email;
//        private String username;
//        private String tel;
//        private String address;
//        private String birth;
//    }


    @Data
    public static class PersonResumeDetailDTO {
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
        private String introduce;
        private List<SkillDTO> skills;

        public PersonResumeDetailDTO(Resume resume) {
            this.id = resume.getId();
            this.userId = resume.getUser().getId();
            this.title = resume.getTitle();
            this.profile = resume.getProfile();
            this.name = resume.getUser().getName();
            this.birth = resume.getUser().getBirth();
            this.tel = resume.getUser().getTel();
            this.address = resume.getUser().getAddress();
            this.email = resume.getUser().getEmail();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.portfolio = resume.getPortfolio();
            this.introduce = resume.getIntroduce();
            this.skills = resume.getSkills().stream().map(SkillDTO::new).toList();
        }

        @Data
        public static class SkillDTO {
            private Integer id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }
    }

    @Data
    public static class PersonResumeUpdateDTO {
        private Integer id;
        private String title;
        private String profile;
        private String profileName;
        private String portfolio;
        private String introduce;
        private String career;
        private String simpleIntroduce;
        private List<SkillDTO> skills;

        public PersonResumeUpdateDTO(Resume resume, List<Skill> skills) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.profile = resume.getProfile();
            this.profileName = resume.getProfileName();
            this.portfolio = resume.getPortfolio();
            this.introduce = resume.getIntroduce();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.skills = skills.stream().map(SkillDTO::new).toList();
        }

        @Data
        public static class SkillDTO {
            private Integer id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }
    }

    @Data
    public static class PersonResumesDTO {
        private Integer id;
        private Integer personId;
        private String profile;
        private String name;
        private String title;
        private String career;
        private String simpleIntroduce;
        private List<ResumeSkillDTO> skills;

        public PersonResumesDTO(Resume resume) {
            this.id = resume.getId();
            this.personId = resume.getUser().getId();
            this.name = resume.getUser().getName();
            this.profile = resume.getProfile();
            this.title = resume.getTitle();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.skills = resume.getSkills().stream().map(ResumeSkillDTO::new).toList();
        }

        @Data
        public static class ResumeSkillDTO {
            private Integer id;
            private String skill;

            public ResumeSkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }
    }
}
