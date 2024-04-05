package com.many.miniproject1.scrap;

import com.many.miniproject1.apply.Apply;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.user.User;
import lombok.Data;

import java.sql.Timestamp;

public class ScrapRequest {

    @Data
    public static class SaveDTO {
        private Resume resume;
        private Post post; // 채용공고 아이디
        private String isPass = "검토중";

        public SaveDTO(Resume resume, Post post) {
            this.resume = resume;
            this.post = post;
        }

        public Apply toEntity() {
            return Apply.builder()
                    .resume(resume)
                    .post(post)
                    .isPass(isPass)
                    .build();
        }
    }
    @Data
    public static class SavePostDTO {
        private Post post;
        private User user;

        public SavePostDTO(User user, Post post) {
            this.user = user;
            this.post = post;
        }

        public Scrap toEntity() {
            return Scrap.builder()
                    .user(user)
                    .post(post)
                    .build();
        }
    }

    @Data
    public static class MainScrapDTO {
        private Resume resume;
        private User user;

        public MainScrapDTO(Resume resume, User user) {
            this.resume = resume;
            this.user = user;
        }

        public Scrap toEntity() {
            return Scrap.builder()
                    .resume(resume)
                    .user(user)
                    .build();
        }
    }

    @Data
    public static class PostChoiceDTO {
        private Integer postChoice;
    }

    @Data
    public static class ResumeChoiceDTO {
        private Integer resumeChoice;
    }
}