package com.many.miniproject1.main;

import lombok.Data;

public class MainRequest {

    @Data
    public static class posChoiceDTO {
        private Integer postChoice;
    }

    @Data
    public static class resumeChoiceDTO {
        private Integer resumeChoice;
    }

}
