package com.many.miniproject1.resume;

import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import com.many.miniproject1.skill.SkillResponse;
import com.many.miniproject1.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;
    private final ResumeQueryRepository resumeQueryRepository;
    private final SkillJPARepository skillJPARepository;

    @Transactional
    public Resume save(ResumeRequest.SaveDTO requestDTO, User sessionUser) {

        Resume resume = resumeJPARepository.save(requestDTO.toEntity());

        List<Skill> skills = new ArrayList<>();
        for (String skillName : requestDTO.getSkills()) {
            SkillResponse.SaveDTO skill = new SkillResponse.SaveDTO();
            skill.setResume(resume);
            skill.setSkill(skillName);
            skills.add(skill.toEntity());
        }

        List<Skill> skillList = skillJPARepository.saveAll(skills);


        return resume;
    }

    public Resume getResumeDetail(ResumeResponse.ResumeDetailDTO respDTO) {
        return resumeJPARepository.findByIdJoinSkillAndUser(respDTO.getId());
    }
}