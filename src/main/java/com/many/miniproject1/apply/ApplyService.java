package com.many.miniproject1.apply;


import com.many.miniproject1.skill.SkillJPARepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyJPARepository applyJPARepository;
    private final ApplyQueryRepository applyQueryRepository;
    private final SkillJPARepository skillJPARepository;

    public List<Apply> companyResumes() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return applyJPARepository.findAll(sort);
    }
}
