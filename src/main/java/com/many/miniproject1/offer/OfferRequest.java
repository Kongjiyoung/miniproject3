package com.many.miniproject1.offer;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import lombok.Data;

public class OfferRequest {
//    @Data
//    public static class SaveDTO {
//        private Integer resumeId;
//        private Integer postId;
//        private Integer companyId;
//        private Integer personId;
//        // DTO에서 날짜를 받을 필요가 없다.
//    }

//    @Data
//    public static class UpdateDTO {
//        private Integer id;
//    }

    @Data
    public static class ScrapOfferDTO {
        private Resume resume;
        private Post post;

        public ScrapOfferDTO(Resume resume, Post post) {
            this.post = post;
            this.resume = resume;
        }

        public Offer toEntity() {
            return Offer.builder()
                    .post(post)
                    .resume(resume)
                    .build();
        }
    }

    @Data
    public static class MainOfferSaveDTO {
        private Integer id;
        private Integer resumeId;
        private Integer postId;

        public MainOfferSaveDTO() {
        }

        public MainOfferSaveDTO(Offer offer) {
            this.id = offer.getId();
            this.resumeId = offer.getResume().getId();
            this.postId = offer.getPost().getId();
        }

        public static Offer toEntity(Resume resume, Post post) {
            return Offer.builder()
                    .post(post)
                    .resume(resume)
                    .build();
        }
    }
}
