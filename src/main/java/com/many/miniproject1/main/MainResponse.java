package com.many.miniproject1.main;

import lombok.Data;

public class MainResponse {


    @Data
    public static class ResumeSkillDTO {
        int resumeId;
        int score;
        public ResumeSkillDTO(int resumeId, int i) {
            this.resumeId = resumeId;
            this.score = i;
        }

    }

    @Data
    public static class PostSkillDTO {
        int postId;
        int score;
        public PostSkillDTO(int postId, int i) {
            this.postId = postId;
            this.score = i;
        }

    }
}
