package com.many.miniproject1.apply;


import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.user.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplyResponse {

    //공고에서 받은 이력서 목록
    @Data
    public static class AppliedResumeSkillDTO {
        private Integer id;
        private String profile;
        private String title;
        private String career;
        private String simpleIntroduce;
        private List<SkillDTO> skllList;
        private String isPass;

        @Builder
        public AppliedResumeSkillDTO(Apply apply, Resume resume, List<Skill> skllList) {
            this.id = apply.getId();
            this.profile = resume.getProfile();
            this.title = resume.getTitle();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.skllList = skllList.stream().map(skill -> {
                return new SkillDTO(skill);
            }).collect(Collectors.toList());
            this.isPass = apply.getIsPass();
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

    //공고에서 받은 이력서 디테일
    @Data
    public static class AppliedResumeSkillDetailDTO {
        // apply
        private Integer id;
        private String isPass;

        // user
        private String name;
        private String birth;
        private String tel;
        private String address;
        private String email;

        // resume
        private String title;
        private String profile;
        private String career;
        private String simpleIntroduce;
        private String portfolio;
        private List<SkillDTO> skllList;
        private String introduce;

        @Builder
        public AppliedResumeSkillDetailDTO(Apply apply, User user, Resume resume, List<Skill> skllList) {
            this.id = apply.getId();
            this.isPass = apply.getIsPass();
            this.name = user.getName();
            this.birth = String.valueOf(user.getBirth());
            this.tel = user.getTel();
            this.address = user.getAddress();
            this.email = user.getEmail();
            this.title = resume.getTitle();
            this.profile = resume.getProfile();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.portfolio = resume.getPortfolio();
            this.skllList = skllList.stream().map(skill -> {
                return new SkillDTO(skill);
            }).collect(Collectors.toList());
            this.introduce = resume.getIntroduce();
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

    //합격/불합격주기
    @Data
    public static class UpdateIsPassDTO {
        private String isPass;

        public UpdateIsPassDTO(String isPass) {
            this.isPass = isPass;
        }
    }

    //개인이 공고 목록보기
    @Data
    public static class ApplyPostSkillDTO {
        private Integer id;
        private String profile;
        private String title;
        private String career;
        private String workingArea;
        private List<SkillDTO> skllList;
        private String isPass;

        @Builder
        public ApplyPostSkillDTO(Apply apply, Post post, List<Skill> skllList) {
            this.id = apply.getId();
            this.profile = post.getProfile();
            this.title = post.getTitle();
            this.career = post.getCareer();
            this.workingArea = post.getWorkingArea();
            this.skllList = skllList.stream().map(skill -> {
                return new SkillDTO(skill);
            }).collect(Collectors.toList());
            this.isPass = apply.getIsPass();
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

    //개인이 공고 디테일보기
    @Data
    public static class ApplyPostSkillDetailDTO {
        // apply
        private Integer id;

        // user
        private String companyName;

        // post
        private String title;
        private String career;
        private String pay;
        private String workCondition;
        private String workStartTime;
        private String workEndTime;
        private String deadline;
        private String task;
        private String profile;
        private String workingArea;
        private List<SkillDTO> skllList;

        public ApplyPostSkillDetailDTO(Apply apply, User user, Post post, List<Skill> skllList) {
            this.id = apply.getId();
            this.companyName = user.getCompanyName();
            this.title = post.getTitle();
            this.career = post.getCareer();
            this.pay = post.getPay();
            this.workCondition = post.getWorkCondition();
            this.workStartTime = post.getWorkStartTime();
            this.workEndTime = post.getWorkEndTime();
            this.deadline = post.getDeadline();
            this.task = post.getTask();
            this.profile = user.getProfile();
            this.workingArea = post.getWorkingArea();
            this.skllList = skllList.stream().map(skill -> {
                return new SkillDTO(skill);
            }).collect(Collectors.toList());
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

    //메인 공고 지원하기
    @Data
    public static class PostApplyDTO {
        private Integer id;
        private Integer resumeId;
        private Integer postId; // 채용공고 아이디
        private String isPass;

        public PostApplyDTO(Apply apply) {
            this.id = apply.getId();
            this.resumeId = apply.getResume().getId();
            this.postId = apply.getPost().getId();
            this.isPass = apply.getIsPass();
        }
    }

//    @Data
//    public static class PostIsPassDTO {
//        private Integer id;
//        private Integer companyId;
//        private String title;
//        private String career;
//        private String pay;
//        private String workCondition;
//        private String workStartTime;
//        private String workEndTime;
//        private String deadline;
//        private String task;
//        private String profile;
//        private String workingArea;
//        private String isPass;
//        private List<String> skill;
//        private Timestamp createdAt;
//
//    }

//    @Data
//    public static class ApplyResumeDTO { // 내가 쓴 이력서
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
//        private String isPass;
//    }

//    @Data
//    public static class ResumeIsPassDTO {
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
//        private String isPass;
//        private List<String> skills;
//
//        public ResumeIsPassDTO(ApplyResumeDTO resumeDTO, List<String> skills) {
//            this.id = resumeDTO.getId();
//            this.personId = resumeDTO.getPersonId();
//            this.title = resumeDTO.getTitle();
//            this.profile = resumeDTO.getProfile();
//            this.portfolio = resumeDTO.getPortfolio();
//            this.introduce = resumeDTO.getIntroduce();
//            this.career = resumeDTO.getCareer();
//            this.simpleIntroduce = resumeDTO.getSimpleIntroduce();
//            this.createdAt = resumeDTO.getCreatedAt();
//            this.email = resumeDTO.getEmail();
//            this.username = resumeDTO.getUsername();
//            this.tel = resumeDTO.getTel();
//            this.address = resumeDTO.getAddress();
//            this.birth = resumeDTO.getBirth();
//            this.isPass = resumeDTO.getIsPass();
//            this.skills = skills;
//        }
//    }

//    @Data
//    public static class CompanyResumeDTO { // 회사에서 받은 이력서
//        private Integer resumeId;
//        private Integer postId;
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
//        private String name;
//        private String tel;
//        private String address;
//        private String birth;
//        private String isPass;
//        private List<ApplySkillDTO> skills = new ArrayList<>();  // 필요 스킬
//
//        public static class ApplySkillDTO {
//        }
//    }

    //  Person이 Apply한  📑Post 목록보기 YSH
//    @Data
//    public static class PersonAppliesDTO {
//        private Integer id;             // 지원 Id
//        private Integer postId;         // 공고 Id
//        private MultipartFile profile;         // 공고 사진
//        private String title;           // 공고 제목
//        private String task;            // 주요 업무
//        private String career;          // 경력
//        private String workingArea;     // 근무 지역
//
//        private List<PostSkillDTO> skills = new ArrayList<>();  // 필요 스킬
//
//        public static class PostSkillDTO {
//
//            private Integer id;
//            private String skill;
//            private int resumeId;
//        }
//    }

}
