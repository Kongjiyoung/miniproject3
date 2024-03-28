package com.many.miniproject1.apply;


import com.many.miniproject1.skill.SkillJPARepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyJPARepository applyJPARepository;
    private final ApplyQueryRepository applyQueryRepository;
    private final SkillJPARepository skillJPARepository;

    public Apply companyResumeDetail(ApplyResponse.CompanyResumeDTO respDTO){
        return applyJPARepository.findByResumeIdJoinSkillAndCompany(respDTO.getPersonId());
    }
}
