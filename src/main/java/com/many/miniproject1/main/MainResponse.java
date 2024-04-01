package com.many.miniproject1.main;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.user.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainResponse {

    @Data
    public static class mainPostsDTO {
        int id;
        String profile;
        String companyName;
        String title;
        String task;
        String career;
        String workingArea;
        List<SkillDTO> skills = new ArrayList<>();

        public mainPostsDTO(Post post) {
            this.id = post.getId();
            this.profile = post.getProfile();
            this.companyName = post.getUser().getCompanyName();
            this.title = post.getTitle();
            this.task = post.getTask();
            this.career = post.getCareer();
            this.workingArea = post.getWorkingArea();
            this.skills = post.getSkillList().stream().map(skill -> new SkillDTO(skill)).toList();
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

    @Data
    public static class PostDetailDTO {
        int id;
        String profile;
        String career;
        String pay;
        String deadline;
        String companyName;
        String task;
        String workStartTime;
        String workEndTime;
        String workingArea;
        String workCondition;
        Boolean isCompany;
        List<SkillDTO> skills = new ArrayList<>();

        public PostDetailDTO(Post post) {
            this.id = post.getId();
            this.profile = post.getProfile();
            this.career = post.getCareer();
            this.pay = post.getPay();
            this.deadline = post.getDeadline();
            this.companyName = post.getUser().getCompanyName();
            this.task = post.getTask();
            this.workStartTime = post.getWorkStartTime();
            this.workEndTime = post.getWorkEndTime();
            this.workingArea = post.getWorkingArea();
            this.workCondition = post.getWorkCondition();
            this.skills = post.getSkillList().stream().map(skill -> new SkillDTO(skill)).toList();
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

    @Data
    public static class ApplyListDTO {
        int resumeId;
        String resumeTitle;

        public ApplyListDTO(Resume resume) {
            this.resumeId = resume.getId();
            this.resumeTitle = resume.getTitle();
        }
    }

    @Data
    public static class ResumeSkillDTO {
        int resumeId;
        int score;

        public ResumeSkillDTO(int resumeId, int i) {
            this.resumeId = resumeId;
            this.score = i;
        }
    }

    @Data
    public static class PostSkillDTO {
        int postId;
        int score;

        public PostSkillDTO(int postId, int i) {
            this.postId = postId;
            this.score = i;
        }
    }

    @Data
    public static class MainResumeDetailDTO {
        // resume
        private Integer id;
        private String title;
        private String career;
        private String simpleIntroduce;
        private String portfolio;
        private String introduce;
        private List<SkillDTO> skllList = new ArrayList<>();

        // user
        private String profile;
        private String name;
        private Date birth;
        private String tel;
        private String address;
        private String email;

        @Builder
        public MainResumeDetailDTO(Resume resume, User user, List<Skill> skllList) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.portfolio = resume.getPortfolio();
            this.introduce = resume.getIntroduce();
            this.skllList = skllList.stream().map(skill -> {
                return new SkillDTO(skill);
            }).collect(Collectors.toList());
            this.profile = resume.getProfile();
            this.name = user.getName();
            this.birth = user.getBirth();
            this.tel = user.getTel();
            this.address = user.getAddress();
            this.email = user.getEmail();
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
}
