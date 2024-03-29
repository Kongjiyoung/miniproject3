package com.many.miniproject1.scrap;

import com.many.miniproject1.offer.Offer;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.user.User;
import lombok.Data;

import java.sql.Timestamp;

public class ScrapRequest {
    @Data
    public static class SaveResumeDTO {
        private Integer resumeId;
        private Integer companyId;
        private Timestamp createdAt;
    }
    @Data
    public static class SavePostDTO {
        private Integer postId;
        private Integer personId;
        private Timestamp createdAt;
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