package com.many.miniproject1.resume;

import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import com.many.miniproject1.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;
    private final ResumeQueryRepository resumeQueryRepository;
    private final SkillJPARepository skillJPARepository;

    @Transactional
    public Resume save(ResumeRequest.SaveDTO requestDTO, User sessionUser) {

        Resume resume = resumeJPARepository.save(requestDTO.toEntity());
        //Skill skill = skillJPARepository.saveAll()
        return resume;
    }

    public Resume getResumeDetail(ResumeResponse.ResumeDetailDTO respDTO) {
        // 이력서를 불러와라
        resumeJPARepository.findById(respDTO.getId());
        // 이력서 아이디와 연결된 스킬을 불러와라.
        return null;
    }
}