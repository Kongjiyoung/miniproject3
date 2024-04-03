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
    public ApplyRequest.UpdateIsPassDTO isPassResume(Integer resumeId, ApplyRequest.UpdateIsPassDTO reqDTO) {
        // 1. 이력서 찾기
        Apply apply = applyJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        apply.setIsPass(reqDTO.getIsPass());
        return new ApplyRequest.UpdateIsPassDTO();
    }

    public List<ApplyResponse.AppliedResumeSkillDTO> getAppliedResumeSkillDTOs(Integer companyId) {
        List<Apply> applyList = applyJPARepository.findByCompanyIdJoinResume(companyId);
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

    public List<ApplyResponse.ApplyPostSkillDTO> getApplyPostSkillDTOs(Integer personId) {
        List<Apply> applyList = applyJPARepository.findByPersonIdJoinPost(personId);
        List<ApplyResponse.ApplyPostSkillDTO> applyPostSkillDTOList = new ArrayList<>();

        applyList.stream().map(apply -> {
            return applyPostSkillDTOList.add(ApplyResponse.ApplyPostSkillDTO.builder()
                    .apply(apply)
                    .post(apply.getPost())
                    .skllList(apply.getPost().getSkillList())
                    .build());
        }).collect(Collectors.toList());

        return applyPostSkillDTOList;
    }

    public Apply companyResumeDetail(int id) {
        Apply apply = applyJPARepository.findByResumeIdJoinSkillAndCompany(id);
        return apply;
    }

    public ApplyResponse.AppliedResumeSkillDetailDTO getAppliedResume(int applyId) {
        Apply apply = applyJPARepository.findResumeByApplyId(applyId);

        return new ApplyResponse.AppliedResumeSkillDetailDTO(apply, apply.getResume().getUser(), apply.getResume(), apply.getResume().getSkillList());
    }


    @Transactional
    public void deleteApply(int applyId) {
        applyJPARepository.deleteById(applyId);
    }

    // 개인이 지원한 이력서 목록 YSH
    public List<Apply> getApplyList(Integer userId) {
        return applyJPARepository.findAllAppliesWithPostsAndSkills(userId);
    }

    public ApplyResponse.ApplyPostSkillDetailDTO getPostDetail(int applyId) {
        Apply apply = applyJPARepository.findPostByApplyId(applyId);

        return new ApplyResponse.ApplyPostSkillDetailDTO(apply, apply.getPost().getUser(), apply.getPost(), apply.getPost().getSkillList());
    }

    public Apply getApplyById(Integer applyId) {
        return applyJPARepository.findByApplyId(applyId);
    }
}
