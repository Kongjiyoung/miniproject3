package com.many.miniproject1.offer;

import lombok.Data;

public class OfferResponse {

    @Data
    public static class  OfferDTO{
        private Integer id;
        private Integer resume_id;
        private Integer post_id;
        private Integer post_writer_id;
        private Integer resume_writer_id;
        private String title;

    }
}
