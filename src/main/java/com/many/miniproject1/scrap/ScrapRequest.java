package com.many.miniproject1.scrap;

<<<<<<< HEAD
=======
import com.many.miniproject1.offer.Offer;
>>>>>>> faaae07d75c873bf5089e369bbf2386873d31815
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