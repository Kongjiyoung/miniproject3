package com.many.miniproject1.post;

import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import com.many.miniproject1.skill.SkillResponse;
import com.many.miniproject1.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostJPARepository postJPARepository;
    private final PostQueryRepository postQueryRepository;
    private final SkillJPARepository skillJPARepository;

    @Transactional
    public Post save(PostRequest.PostSaveDTO reqDTO, User sessionUser){
        Post post = postJPARepository.save(reqDTO.toEntity(sessionUser));

        List<Skill> skills = new ArrayList<>();
        for (String skillName : reqDTO.getSkill()) {
            SkillResponse.SaveDTO skill = new SkillResponse.SaveDTO();
            skill.setSkill(skillName);
            skills.add(skill.toEntity());
        }

        List<Skill> skillList = skillJPARepository.saveAll(skills);
        return post;
    }
    public List<Post> getResumeList(Integer userId){
        return postJPARepository.findByUserIdJoinSkillAndUser(userId);
    }
}
