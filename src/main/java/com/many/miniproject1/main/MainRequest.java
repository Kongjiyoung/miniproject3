package com.many.miniproject1.main;

import lombok.Data;

public class MainRequest {

    @Data
    public static class PostChoiceDTO {
        private Integer postChoice;
    }

    @Data
    public static class ResumeChoiceDTO {
        private Integer resumeChoice;
    }

}
