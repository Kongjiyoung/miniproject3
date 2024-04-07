package com.many.miniproject1.scrap;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.user.User;
import lombok.Data;

public class ScrapRequest {
    @Data
    public static class SaveResumeDTO {
        private User user;
        private Resume resume;

        public SaveResumeDTO(User user, Resume resume) {
            this.user = user;
            this.resume = resume;
        }

        public Scrap toEntity() {
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

        public Scrap toEntity() {
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
    public static class ScrapResumeDTO {
        private Integer id;
        private Resume resume;
        private User user;

        public ScrapResumeDTO(Resume resume, User user) {
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

    // TODO: 디티오 이름 나중에 바꾸기. 위에 동일한 이름이 있음.
    @Data
    public static class ScrapResumeeDTO {
        private Integer resumeId;
        private Integer companyId;
    }


}