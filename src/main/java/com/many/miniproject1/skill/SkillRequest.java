package com.many.miniproject1.skill;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import lombok.Data;

public class SkillRequest {

    @Data
    public static class SaveResumeDTO {
        private String skill;
        private Resume resume;

        public Skill toEntity(String skill, Resume resume) {
            return Skill.builder()
                    .skill(skill)
                    .resume(resume)
                    .build();
        }
    }

    @Data
    public static class SavePostDTO{
        private String skill;
        private Post post;

        public Skill toEntity(String skill, Post post){
            return Skill.builder()
                    .skill(skill)
                    .post(post)
                    .build();
        }
    }
}