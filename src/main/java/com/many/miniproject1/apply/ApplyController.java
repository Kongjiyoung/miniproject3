package com.many.miniproject1.apply;


import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplyController {
    private final HttpSession session;
    private final ApplyService applyService;

    // 기업에서 받은 이력서 관리
    @GetMapping("/api/company/resumes")
    public ResponseEntity<?> companyResumes() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ApplyResponse.AppliedResumeSkillDTO> appliedResumeSkillDTOList = applyService.getAppliedResumeSkillDTOs(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(appliedResumeSkillDTOList));
    }

    @GetMapping("/api/company/resumes/{id}")
    public ResponseEntity<?> companyResumeDetail(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ApplyResponse.AppliedResumeSkillDetailDTO appliedResumeDetail = applyService.getAppliedResume(id);
        applyService.companyResumeDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(appliedResumeDetail));
    }

    @PutMapping("/api/company/resumes/{id}/is-pass")
    public ResponseEntity<?> companyPass(@PathVariable Integer id, @RequestBody ApplyRequest.UpdateIsPassDTO reqDTO) {
        Apply apply = applyService.getApplyById(id);
        ApplyRequest.UpdateIsPassDTO updateIsPassDTO = applyService.isPassResume(id, reqDTO);
        apply.updateIsPass(reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(updateIsPassDTO));
    }

    // 개인이 지원한 이력서 목록
    @GetMapping("/api/person/applies")
    public ResponseEntity<?> personApplies() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ApplyResponse.ApplyPostSkillDTO> applyPostSkillDTOList = applyService.getApplyPostSkillDTOs(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(applyPostSkillDTOList));
    }

    @GetMapping("/api/person/applies/{id}") // 내가 지원한 공고 디테일
    public ResponseEntity<?> personApply(@PathVariable int id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ApplyResponse.ApplyPostSkillDetailDTO applyPostDetail = applyService.getPostDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(applyPostDetail));
    }

    @DeleteMapping("/api/person/applies/{id}")
    public ResponseEntity<?> applyDelete(@PathVariable int id) {
        applyService.deleteApply(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}