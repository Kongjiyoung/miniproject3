package com.many.miniproject1.post;

import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.user.SessionUser;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class PostResponse {

    @Data
    static class PostListDTO {
        private Integer id;
        private Integer userId;
        private String companyName;
        private String career;
        private String workingArea;
        private String profile;
        private List<PostSkillDTO> skillList;

        public PostListDTO(Post post) {
            this.id = post.getId();
            this.userId = post.getUser().getId();
            this.companyName = post.getUser().getCompanyName();
            this.career = post.getCareer();
            this.workingArea = post.getWorkingArea();
            this.profile = post.getProfile();
            this.skillList = post.getSkillList().stream().map(PostSkillDTO::new).toList();
        }

        @Data
        public static class PostSkillDTO {
            private Integer id;
            private String skill;

            public PostSkillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }


    }

    @Data
    public static class DetailDTO {
        private Integer id;
        private Integer companyId;
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
        private List<skillDTO> skillList;


        public DetailDTO(Post post) {
            this.id = post.getId();
            this.companyId = post.getId();
            this.title = post.getTitle();
            this.career = post.getCareer();
            this.pay = post.getPay();
            this.workCondition = post.getWorkCondition();
            this.workStartTime = post.getWorkStartTime();
            this.workEndTime = post.getWorkEndTime();
            this.deadline = post.getDeadline();
            this.task = post.getTask();
            this.profile = post.getProfile();
            this.workingArea = post.getWorkingArea();
            this.skillList = post.getSkillList().stream().map(skill -> new skillDTO(skill)).toList();
        }

        @Data
        public static class skillDTO {
            private Integer id;
            private String skill;

            public skillDTO(Skill skill) {
                this.id = skill.getId();
                this.skill = skill.getSkill();
            }
        }
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
        private Integer companyId;
        private String title;
        private String career;
        private String pay;
        private String workCondition;
        private String workStartTime;
        private String workEndTime;
        private String deadline;
        private String task;
        private String profile;
        private List<String> skills; // 기존 스킬을 삭제하고, 인서트하기
        private String workingArea;
    }

    @Data
    public static class PostProfileDTO {
        private Integer id;
        private Integer companyId;
        private String title;
        private String career;
        private String pay;
        private String workCondition;
        private String workStartTime;
        private String workEndTime;
        private String deadline;
        private String task;
        private String profile; // 얘가 다른 애
        private List<String> skill;
        private String workingArea;
    }

    // 공고 상세보기 YSH
    @Data
    public static class PostDetailDTO {
        private Integer id;             // postId
        private String title;           // 공고 제목
        private String career;          // 경력
        private String pay;             // 최소 연봉
        private String deadline;        // 마감일
        private String task;            // 주요 업무
        private String workStartTime;   // 근무 시간 Start
        private String workEndTime;     // 근무 시간 End
        private String workingArea;     // 근무 지역
        private String workCondition;   // 혜택 및 복지
        // companyName Join
        private String companyName;     // 회사명 JOIN User

        private List<PostSkillDTO> skills = new ArrayList<>();  // 필요 스킬

        public static class PostSkillDTO {
            private Integer id;
            private String skill;
            private int resumeId;
        }
    }

    @Data
    public static class PostDTO {
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
        private List<SkillDTO> skills;

        public PostDTO(Post post, List<Skill> skills) {
            this.id = post.getId();
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
            this.skills = skills.stream().map(skill -> (new SkillDTO(skill))).toList();

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
    public static class PostUpdateDTO {
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
        private List<SkillDTO> skills;

        @Builder
        public PostUpdateDTO(Post post, List<Skill> skillList) {
            this.id = post.getId();
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
            this.skills = skillList.stream().map(skill -> (new SkillDTO(skill))).toList();

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