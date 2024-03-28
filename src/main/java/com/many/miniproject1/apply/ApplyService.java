package com.many.miniproject1.apply;


import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeJPARepository;
import com.many.miniproject1.skill.SkillJPARepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyJPARepository applyJPARepository;
    private final ApplyQueryRepository applyQueryRepository;
    private final SkillJPARepository skillJPARepository;

    @Transactional
    public Apply isPassResume(ApplyRequest.UpdateIsPass reqDTO) {
        // 1. 이력서 찾기
        Apply apply = applyJPARepository.findById(reqDTO.getId())
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        System.out.println(apply);

        apply.setIsPass(reqDTO.getIsPass());
        return apply;
    }

    public Apply findById(int id) {
        Apply apply = applyJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        return apply;
    }
}
