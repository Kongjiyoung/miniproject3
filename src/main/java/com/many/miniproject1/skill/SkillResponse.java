package com.many.miniproject1.skill;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import lombok.Data;

public class SkillResponse {
    // 일단 만들어봤어요. 지워도됨.
    @Data
    public static class SaveResumeDTO {
        private String skill;
        private Resume resume;

        public Skill toEntity() {
            return Skill.builder()
                    .skill(skill)
                    .resume(resume)
                    .build();
        }
    }
    @Data
    public static class PostSaveDTO{
        private String skill;
        private Post post;

        public Skill toEntity(){
            return Skill.builder()
                    .skill(skill)
                    .post(post)
                    .build();
        }
    }
}