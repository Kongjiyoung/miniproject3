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

    //메인 이력서 목록
    @Data
    public static class MainResumesDTO {
        private Integer id;
        private String profile;
        private String title;
        private String career;
        private String simplerIntroduce;
        private List<SkillDTO> sklls = new ArrayList<>();

        @Data
        public class SkillDTO {
            private Integer id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }

        public MainResumesDTO(Resume resume) {
            this.id = resume.getId();
            this.profile = resume.getProfile();
            this.title = resume.getTitle();
            this.career = resume.getCareer();
            this.simplerIntroduce = resume.getSimpleIntroduce();
            this.sklls = resume.getSkills().stream().map(skill -> new SkillDTO(skill)).toList();
        }
    }

    //
    @Data
    public static class PostTitleListDTO {
        private Integer id;
        private String title;

        @Builder
        public PostTitleListDTO(Integer id, String title) {
            this.id = id;
            this.title = title;
        }
    }

    @Data
    public static class MainResumeDetailDTO { // 이력서, 유저 + 로그인한 유저의 이력서
        // resume
        private Integer id;
        private String title;
        private String career;
        private String simpleIntroduce;
        private String portfolio;
        private String introduce;
        private List<SkillDTO> skillList;

        // user
        private Integer companyId;
        private String profile;
        private String name;
        private Date birth;
        private String tel;
        private String address;
        private String email;

//        // post
//        private List<PostDTO> postList;

        @Builder
        public MainResumeDetailDTO(Resume resume, User user, List<Skill> skillList) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.career = resume.getCareer();
            this.simpleIntroduce = resume.getSimpleIntroduce();
            this.portfolio = resume.getPortfolio();
            this.introduce = resume.getIntroduce();
            this.profile = resume.getProfile();
            this.skillList = skillList.stream().map(skill -> {
                return new SkillDTO(skill);
            }).collect(Collectors.toList());
            this.companyId = user.getId();
            this.name = user.getName();
            this.birth = Date.valueOf(user.getBirth());
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
    //메인 채용공고 목록
    @Data
    public static class MainPostsDTO {
        private Integer id;
        private String profile;
        private String companyName;
        private String title;
        private String task;
        private String career;
        private String workingArea;
        private List<SkillDTO> skills = new ArrayList<>();

        public MainPostsDTO(Post post) {
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
            private Integer id;
            private String skill;

            public SkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }
    }

    //메인 채용공고 디테일
    @Data
    public static class PostDetailDTO {
        private Integer id;
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
        private Boolean isPerson;
        private List<SkillDTO> skills = new ArrayList<>();
        private List<ResumesDTO> resumes = new ArrayList<>();

        public PostDetailDTO(Post post, Boolean isPerson) {
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
            this.isPerson = isPerson;
            this.skills = post.getSkillList().stream().map(skill -> new SkillDTO(skill)).toList();

        }

        public PostDetailDTO(Post post, List<Resume> resumes, Boolean isPerson) {
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
            this.isPerson = isPerson;
            this.skills = post.getSkillList().stream().map(skill -> new SkillDTO(skill)).toList();
            this.resumes = resumes.stream().map(resume -> new ResumesDTO(resume)).toList();

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

        @Data
        public class ResumesDTO {
            private Integer id;
            private String title;

            public ResumesDTO(Resume resume) {
                this.id = resume.getId();
                this.title = resume.getTitle();
            }
        }


    }

    //매칭할 공고 선택목록
    @Data
    public static class PostMatchingChoiceDTO {
        private Integer postId;
        private String postTitle;

        public PostMatchingChoiceDTO(Post post) {
            this.postId = post.getId();
            this.postTitle = post.getTitle();
        }
    }

    //메인 매칭받은 이력서 목록과 공고 선택
    @Data
    public static class MainPostMatchDTO {
        private PostDTO postChoice;
        private List<MainResponse.MainResumesDTO> mainPostDTO;

        public MainPostMatchDTO(Post post, List<MainResponse.MainResumesDTO> mainPostDTO) {
            this.postChoice = new PostDTO(post);
            this.mainPostDTO = mainPostDTO;
        }

        @Data
        public static class PostDTO {
            private Integer postId;
            private String postTitle;

            public PostDTO(Post post) {
                this.postId = post.getId();
                this.postTitle = post.getTitle();
            }
        }
    }


    //매칭할 이력서 선택목록
    @Data
    public static class ResumeMatchingChoiceDTO {
        private Integer resumeId;
        private String resumeTitle;

        public ResumeMatchingChoiceDTO(Resume resume) {
            this.resumeId = resume.getId();
            this.resumeTitle = resume.getTitle();
        }
    }

    //메인 매칭받은 이력서 목록과 공고 선택
    @Data
    public static class MainResumeMatchDTO {
        private ResumeDTO resumeChoice;
        private List<MainResponse.MainPostsDTO> mainPostDTO;

        public MainResumeMatchDTO(Resume Resume, List<MainResponse.MainPostsDTO> mainPostsDTO) {
            this.resumeChoice = new ResumeDTO(Resume);
            this.mainPostDTO = mainPostsDTO;
        }

        @Data
        public static class ResumeDTO {
            private Integer postId;
            private String postTitle;

            public ResumeDTO(Resume resume) {
                this.postId = resume.getId();
                this.postTitle = resume.getTitle();
            }
        }
    }
    //매칭 이력서 점수 DTO
    @Data
    public static class ResumeSkillDTO {
        private Integer resumeId;
        private Integer score;

        public ResumeSkillDTO(int resumeId, int i) {
            this.resumeId = resumeId;
            this.score = i;
        }
    }

    //매칭 공고 점수 DTO
    @Data
    public static class PostSkillDTO {
        private Integer postId;
        private Integer score;

        public PostSkillDTO(int postId, int i) {
            this.postId = postId;
            this.score = i;
        }
    }
}