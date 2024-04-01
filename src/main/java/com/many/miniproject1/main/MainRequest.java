package com.many.miniproject1.main;

import lombok.Data;

public class MainRequest {

    @Data
    public static class postIdDTO {
        private Integer postId;
    }

    @Data
    public static class resumeChoiceDTO {
        private int resumeChoice;
    }

}
