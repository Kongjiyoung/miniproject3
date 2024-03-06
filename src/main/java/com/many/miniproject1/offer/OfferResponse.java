package com.many.miniproject1.offer;

import lombok.Data;

public class OfferResponse {

    @Data
    public static class SaveDTO{
        private Integer id;
        private Integer resumeId;
        private Integer postId;
        private Integer companyId;
        private Integer personId;
    }
}
