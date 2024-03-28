package com.many.miniproject1.apply;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import lombok.Data;


public class ApplyRequest {
    @Data
    public static class SaveDTO {
        private Resume resume;
        private Post post; // 채용공고 아이디

        public SaveDTO(Resume resume, Post post) {
            this.resume = resume;
            this.post = post;
        }

        public Apply toEntity(){
            return Apply.builder()
                    .resume(resume)
                    .post(post)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private String isPass;
    }

    @Data
    public static class UpdateIsPass {
        private Integer id;
        private String isPass;
    }
}