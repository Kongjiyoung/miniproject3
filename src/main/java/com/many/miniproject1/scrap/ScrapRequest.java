package com.many.miniproject1.scrap;

import lombok.Data;

public class ScrapRequest {
    @Data
    public static class SaveDTO {
        private Integer id;
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
    }
}