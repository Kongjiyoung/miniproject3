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


    //공고에서 받은 이력서 목록
    public List<ApplyResponse.AppliedResumeSkillDTO> getAppliedResumeSkillDTOs(Integer companyId) {
        List<Apply> applyList = applyJPARepository.findByCompanyIdJoinResume(companyId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        List<ApplyResponse.AppliedResumeSkillDTO> appliedResumeSkillDTOList = new ArrayList<>();

        applyList.stream().map(apply -> {
            return appliedResumeSkillDTOList.add(ApplyResponse.AppliedResumeSkillDTO.builder()
                    .apply(apply)
                    .resume(apply.getResume())
                    .skllList(apply.getResume().getSkills())
                    .build());
        }).collect(Collectors.toList());

        return appliedResumeSkillDTOList;
    }

    //공고에서 받은 이력서 디테일
    public ApplyResponse.AppliedResumeSkillDetailDTO getAppliedResume(int applyId) {
        Apply apply = applyJPARepository.findResumeByApplyId(applyId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        return new ApplyResponse.AppliedResumeSkillDetailDTO(apply, apply.getResume().getUser(), apply.getResume(), apply.getResume().getSkills());
    }

    //합격/불합격주기
    @Transactional
    public ApplyResponse.UpdateIsPassDTO isPassResume(Integer resumeId, ApplyRequest.UpdateIsPassDTO reqDTO) {
        // 1. 이력서 찾기
        Apply apply = applyJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        apply.setIsPass(reqDTO.getIsPass());
        return new ApplyResponse.UpdateIsPassDTO(apply.getIsPass());
    }

    //개인이 공고 목록보기
    public List<ApplyResponse.ApplyPostSkillDTO> getApplyPostSkillDTOs(Integer personId) {
        List<Apply> applyList = applyJPARepository.findByPersonIdJoinPost(personId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다"));
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

    //개인이 공고 디테일보기
    public ApplyResponse.ApplyPostSkillDetailDTO getPostDetail(int applyId) {
        Apply apply = applyJPARepository.findPostByApplyId(applyId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다"));
        ;

        return new ApplyResponse.ApplyPostSkillDetailDTO(apply, apply.getPost().getUser(), apply.getPost(), apply.getPost().getSkillList());
    }

    public Apply companyResumeDetail(int id) {
        Apply apply = applyJPARepository.findByResumeIdJoinSkillAndCompany(id)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        return apply;
    }

    @Transactional
    public void deleteApply(int applyId) {
        applyJPARepository.deleteById(applyId);
    }

    // 개인이 지원한 이력서 목록 YSH
    public List<ApplyResponse.ApplyDTO> getApplyList(Integer userId) {
        List<Apply> applyList = applyJPARepository.findAllAppliesWithPostsAndSkills(userId)
                .orElseThrow(() -> new Exception404("해당 자원을 찾을 수 없습니다"));
        return applyList.stream().map(ApplyResponse.ApplyDTO::new).toList();
    }

    public ApplyResponse.ApplyDTO getApplyById(Integer applyId) {
        Apply apply = applyJPARepository.findById(applyId)
                .orElseThrow(() -> new Exception404("해당 지원을 찾을 수 없습니다"));
        return new ApplyResponse.ApplyDTO(apply);
    }
}
