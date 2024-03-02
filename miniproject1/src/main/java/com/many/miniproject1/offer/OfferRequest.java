package com.many.miniproject1.offer;

import lombok.Data;

public class OfferRequest {
    @Data
    public class SaveDTO {
        private Integer id;
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
    }
}
