package com.many.miniproject1.apply;


import com.many.miniproject1._core.common.ProfileImageService;
import com.many.miniproject1.post.Post;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.CriteriaBuilder;

import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.user.User;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ApplyResponse {
    //    @Data
//    public static class ApplyPostDTO {
//        private Integer id;
//        private Integer companyId;
////        private String title;
////        private String career;
////        private String pay;
////        private String workCondition;
////        private String workStartTime;
////        private String workEndTime;
////        private String deadline;
////        private String task;
////        private String profile;
////        private String workingArea;
//        private String isPass;
//        private Timestamp createdAt;
//    }


    @Data
    public static class PostIsPassDTO {
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

    }

    @Data
    public static class ApplyResumeDTO {
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
    public static class ResumeIsPassDTO {
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

        public ResumeIsPassDTO(ApplyResumeDTO resumeDTO, List<String> skills) {
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

    //  Person이 Apply한  📑Post 목록보기 YSH
    @Data
    public static class PersonAppliesDTO {
        private Integer id;             // 지원 Id
        private Integer postId;         // 공고 Id
        private MultipartFile profile;         // 공고 사진
        private String title;           // 공고 제목
        private String task;            // 주요 업무
        private String career;          // 경력
        private String workingArea;     // 근무 지역

        private List<PostSkillDTO> skills = new ArrayList<>();  // 필요 스킬

        public static class PostSkillDTO {
            private Integer id;
            private String skill;
            private int resumeId;
        }
    }
}
