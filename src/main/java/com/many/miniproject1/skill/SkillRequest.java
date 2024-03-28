package com.many.miniproject1.skill;

import com.many.miniproject1.post.Post;
import lombok.Data;

import java.sql.Timestamp;

public class SkillRequest {
    @Data
    public static class SaveDTO {
        private Integer id;
        private String skill;
        private Integer resumeId;
        private Integer postId; // null 허용 되어야 한다
    }

    @Data static class ResetDTO{
        private Integer id;
        private String skill;
        private Integer resumeId;
        private Integer postId;
    }

    @Data
    public static class UpdatePostSkillsDTO {
        private String skill;
        private Post post;

        public Skill toEntity() {
            return Skill.builder()
                    .skill(skill)
                    .post(post)
                    .build();
        }
    }
}