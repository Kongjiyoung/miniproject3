package com.many.miniproject1.scrap;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.user.User;
import lombok.Data;

import java.sql.Timestamp;

public class ScrapRequest {
    @Data
    public static class SaveResumeDTO {
        private User user;
        private Resume resume;
        public SaveResumeDTO(User user, Resume resume){
            this.user =user;
            this.resume=resume;
        }
        public Scrap toEntity(){
            return Scrap.builder()
                    .resume(resume)
                    .user(user)
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
        public Scrap toEntity(){
            return Scrap.builder()
                    .user(user)
                    .post(post)
                    .build();
        }

    }

    @Data
    public static class UpdateDTO {
        private Integer id;
    }

    @Data
    public static class MainScrapDTO{
        private Resume resume;
        private User user;

        public MainScrapDTO(Resume resume, User user) {
            this.resume = resume;
            this.user = user;
        }

        public Scrap toEntity(){
            return Scrap.builder()
                    .resume(resume)
                    .user(user)
                    .build();
        }
    }
}