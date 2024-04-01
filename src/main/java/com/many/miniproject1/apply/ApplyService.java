package com.many.miniproject1.apply;


import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.skill.SkillJPARepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyJPARepository applyJPARepository;
    private final SkillJPARepository skillJPARepository;


    @Transactional
    public Apply isPassResume(Integer resumeId, ApplyRequest.UpdateIsPass reqDTO) {
        // 1. 이력서 찾기
        Apply apply = applyJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        System.out.println(apply);

        apply.setIsPass(reqDTO.getIsPass());
        return apply;
    }

    public List<ApplyResponse.AppliedResumeSkillDTO> appliedResumeSkillDTOs(Integer companyId) {
        List<Apply> applyList = applyJPARepository.findByUserIdJoinPost(companyId);
        List<ApplyResponse.AppliedResumeSkillDTO> appliedResumeSkillDTOList = new ArrayList<>();

        applyList.stream().map(apply -> {
            return appliedResumeSkillDTOList.add(ApplyResponse.AppliedResumeSkillDTO.builder()
                    .apply(apply)
                    .resume(apply.getResume())
                    .skllList(apply.getResume().getSkillList())
                    .build());
        }).collect(Collectors.toList());

        return appliedResumeSkillDTOList;
    }

    public Apply companyResumeDetail(int id) {
        Apply apply = applyJPARepository.findByResumeIdJoinSkillAndCompany(id);
        return apply;
    }

    public Apply findById(int id) {
        Apply apply = applyJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        return apply;
    }


    @Transactional
    public void deleteApply(int id) {
        // applyJPARepository.deleteApplyPostById(id);
        applyJPARepository.deleteById(id);
    }

    // 개인이 지원한 이력서 목록 YSH
    public List<Apply> getApplyList(Integer userId) {
        return applyJPARepository.findAllAppliesWithPostsAndSkills(userId);
    }

    public Apply getPostDetail(Integer userId, Integer postId) {
        Apply apply = applyJPARepository.findByPostIdJoinPostAndSkillAndUser(postId, userId).orElseThrow(() -> new Exception404("없음"));
        return apply;
    }

}
