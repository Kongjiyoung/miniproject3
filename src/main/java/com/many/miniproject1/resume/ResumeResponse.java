package com.many.miniproject1.resume;

import com.many.miniproject1.skill.Skill;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class ResumeResponse {

    //이력서 목록
    @Data
    public static class ResumeListDTO {
        int id;
        Integer personId;
        String profile;
        String name;
        String title;
        String career;
        String simpleIntroduce;
        List<ResumeSkillDTO> skills;

        public ResumeListDTO(Resume resume) {
            this.id = resume.getId();
            this.personId = resume.getUser().getId();
            this.name = resume.getUser().getName();
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
    //이력서 디테일 DTO
    @Data
    public static class ResumeDetailDTO {
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
        private List<ResumeDetailDTO.ResumeSkillDTO> skills;

        public ResumeDetailDTO(Resume resume) {
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
            this.skills = resume.getSkills().stream().map(skill -> new ResumeDetailDTO.ResumeSkillDTO(skill)).toList();
        }

        @Data
        public class ResumeSkillDTO {
            private Integer id;
            private String skill;

            public ResumeSkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }
    }
    // 이력서 저장 DTO
    @Data
    public static class ResumeSaveDTO {
        private Integer resumeId;
        private String title;
        private String profile;
        private String profileName;
        private String career;
        private String simpleIntroduce;
        private String portfolio;
        private String introduce;
        private List<SkillDTO> skills;

        public ResumeSaveDTO(Resume resume, List<Skill> skills) {
            this.resumeId = resume.getId();
            this.title = resume.getTitle();
            this.profile = resume.getProfile();
            this.profileName = resume.getProfileName();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.portfolio = resume.getPortfolio();
            this.introduce = resume.getIntroduce();
            this.skills = skills.stream().map(skill -> new SkillDTO(skill)).toList();
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
    public static class UpdateDTO {
        private Integer id;
        private String title;
        private String profile;
        private String profileName;
        private String portfolio;
        private String introduce;
        private String career;
        private String simpleIntroduce;
        private List<SkillDTO> skills;

        public UpdateDTO(Resume resume, List<Skill> skills) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.profile = resume.getProfile();
            this.profileName = resume.getProfileName();
            this.portfolio = resume.getPortfolio();
            this.introduce = resume.getIntroduce();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.skills = skills.stream().map(skill -> (new SkillDTO(skill))).toList();
        }

        @Data
        public class SkillDTO {
            private int id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }
    }


}
