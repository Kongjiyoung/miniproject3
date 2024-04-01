package com.many.miniproject1.scrap;

import com.many.miniproject1.offer.OfferResponse;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.user.User;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ScrapResponse {
    @Data
    public static class PostScrapSaveDTO{
        private User user;
        private Post post;

        public PostScrapSaveDTO(Scrap scrap) {
            this.user = scrap.getUser();
            this.post = scrap.getPost();
        }
    }

    @Data
    public static class ScarpPostDTO {
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
    public static class ScarpPostSkillDTO {
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
//

        public ScarpPostSkillDTO(ScrapResponse.ScarpPostDTO post, List<String> skills) {
            this.id = post.getId();
            this.companyId = post.getCompanyId();
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
            this.skill = skills;
            this.createdAt = post.getCreatedAt();
        }
    }

    @Data
    public static class ScrapResumeDTO {
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
        private String isPass;
    }

    @Data
    public static class ScrapResumeSkillDTO {
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
        private String isPass;
        private List<String> skills;

        public ScrapResumeSkillDTO(ScrapResponse.ScrapResumeDTO resumeDTO, List<String> skills) {
            this.id = resumeDTO.getId();
            this.personId = resumeDTO.getPersonId();
            this.title = resumeDTO.getTitle();
            this.profile = resumeDTO.getProfile();
            this.portfolio = resumeDTO.getPortfolio();
            this.introduce = resumeDTO.getIntroduce();
            this.career = resumeDTO.getCareer();
            this.simpleIntroduce = resumeDTO.getSimpleIntroduce();
            this.createdAt = resumeDTO.getCreatedAt();
            this.email = resumeDTO.getEmail();
            this.username = resumeDTO.getUsername();
            this.tel = resumeDTO.getTel();
            this.address = resumeDTO.getAddress();
            this.birth = resumeDTO.getBirth();
            this.isPass = resumeDTO.getIsPass();
            this.skills = skills;
        }
    }

}


