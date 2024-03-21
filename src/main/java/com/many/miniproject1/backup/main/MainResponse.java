package com.many.miniproject1.backup.main;

import com.many.miniproject1.backup.post.Post;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class MainResponse {

    @Data
    public static class postDTO {
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

        private List<String> skill;

        private Timestamp createdAt;


        public postDTO(Post post, List<String> skills) {
            this.id = post.getId();
            this.companyId =  post.getCompanyId();
            this.title =  post.getTitle();
            this.career =  post.getCareer();
            this.pay =  post.getPay();
            this.workCondition =  post.getWorkCondition();
            this.workStartTime =  post.getWorkStartTime();
            this.workEndTime =  post.getWorkEndTime();
            this.deadline =  post.getDeadline();
            this.task =  post.getTask();
            this.profile =  post.getProfile();
            this.workingArea =  post.getWorkingArea();
            this.skill = skills;
            this.createdAt =  post.getCreatedAt();
        }
    }


    @Data
    public static class ResumeSkillDTO{
        private int resumeId;
        private int score;

        public ResumeSkillDTO(int resumeId, int score) {
            this.resumeId = resumeId;
            this.score = score;
        }
    }

    @Data
    public static class PostSkillDTO{
        private int postId;
        private int score;

        public PostSkillDTO(int postId, int score) {
            this.postId = postId;
            this.score = score;
        }
    }


}
