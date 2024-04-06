package com.many.miniproject1.skill;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import lombok.Data;

public class SkillRequest {

//TODO: 안쓰면 삭제
//    @Data static class ResetDTO{
//        private Integer id;
//        private String skill;
//        private Integer resumeId;
//        private Integer postId;
//    }


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