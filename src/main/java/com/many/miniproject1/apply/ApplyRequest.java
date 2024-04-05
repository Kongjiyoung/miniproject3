package com.many.miniproject1.apply;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import lombok.Data;


public class ApplyRequest {

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

    // 이력서 합격/불합격 여부 결정
    @Data
    public static class UpdateIsPassDTO {
        private String isPass;
    }
}