package com.many.miniproject1.offer;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

public class OfferRequest {
    @Data
    public static class SaveDTO {
        private Integer resumeId;
        private Integer postId;
        private Integer companyId;
        private Integer personId;
        // DTO에서 날짜를 받을 필요가 없다.
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
    }

    @Data
    public static class ScrapOfferDTO{
        private Resume resume;
        private Post post;

        public ScrapOfferDTO(Resume resume, Post post) {
            this.post = post;
            this.resume = resume;
        }

        public Offer toEntity(){
            return Offer.builder()
                    .post(post)
                    .resume(resume)
                    .build();
        }
    }
}
