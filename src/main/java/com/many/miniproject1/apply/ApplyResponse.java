package com.many.miniproject1.apply;

import com.many.miniproject1.post.Post;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class ApplyResponse {
    @Data
    public static class applyDetailDTO {
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
        private String isPass;
        private Timestamp createdAt;
    }

    @Data
    public static class postIsPassDTO {
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
        private String isPass;
        private List<String> skill;
        private Timestamp createdAt;
//

        public postIsPassDTO(ApplyResponse.applyDetailDTO post, List<String> skills) {
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
            this.isPass=post.getIsPass();
            this.skill = skills;
            this.createdAt =  post.getCreatedAt();
        }
    }
}
