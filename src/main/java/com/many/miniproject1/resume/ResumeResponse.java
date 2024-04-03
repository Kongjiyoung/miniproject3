package com.many.miniproject1.resume;

import com.many.miniproject1.offer.OfferResponse;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.user.User;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResumeResponse {

    // 04-02 YSH
    @Data
    public static class ResumeSaveDTO{
        int resumeId;
        String title;
        String profile;
        String profileName;
        String career;
        String simpleIntroduce;
        String portfolio;
        String introduce;
        List<SkillDTO> skills;

        public ResumeSaveDTO(Resume resume, List<Skill> skills) {
            this.resumeId = resume.getId();
            this.title = resume.getTitle();
            this.profile = resume.getProfile();
            this.profileName= resume.getProfileName();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.portfolio = resume.getPortfolio();
            this.introduce = resume.getIntroduce();
            this.skills = skills.stream().map(skill -> new SkillDTO(skill)).toList();
        }

        @Data
        public static class SkillDTO{
            private int id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }
    }

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

        public DetailSkillDTO(Resume resume, List<Skill> skills) {

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
    public static class resumeDetailDTO {
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
        private List<resumeDetailDTO.ResumeSkillDTO> skills;
        private String introduce;

        public resumeDetailDTO(Resume resume, User sessionUser) {
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
            this.skills = resume.getSkills().stream().map(skill -> new resumeDetailDTO.ResumeSkillDTO(skill)).toList();
            this.introduce = resume.getIntroduce();
        }

        @Data
        public class ResumeSkillDTO {
            private int id;
            private String skill;

            public ResumeSkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }
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

    @Data
    public static class resumeListDTO {
        int id;
        Integer personId;
        String profile;
        String title;
        String career;
        String simpleIntroduce;
        List<ResumeSkillDTO> skills;

        public resumeListDTO(Resume resume) {
            this.id = resume.getId();
            this.personId = resume.getUser().getId();
            this.profile = resume.getProfile();
            this.title = resume.getTitle();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.skills = resume.getSkills().stream().map(skill -> new ResumeSkillDTO(skill)).toList();
        }

        @Data
        public class ResumeSkillDTO {
            private int id;
            private String skill;

            public ResumeSkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }
    }
}
