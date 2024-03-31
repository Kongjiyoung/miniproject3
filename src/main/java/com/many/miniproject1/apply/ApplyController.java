package com.many.miniproject1.apply;


import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.user.User;
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
        User sessionUser = (User) session.getAttribute("sessionUser");
        //List<Apply> applyList = applyService.companyResumes(sessionUser.getId());
        List<ApplyResponse.AppliedResumeSkillDTO> appliedResumeSkillDTOList = applyService.appliedResumeSkillDTOs(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(appliedResumeSkillDTOList));
    }

    @GetMapping("/api/company/resumes/{id}")
    public ResponseEntity<?> companyResumeDetail(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Apply apply = applyService.findById(id);
        applyService.companyResumeDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(apply));
    }

    // TODO: 테스트 다시 하기
    @PutMapping("/api/company/resumes/{id}/is-pass")
    public ResponseEntity<?> companyPass(@PathVariable int id, @RequestBody ApplyRequest.UpdateIsPass reqDTO) {
        Apply apply = applyService.isPassResume(id, reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(apply));
    }

    // 개인이 지원한 이력서 목록
    // TODO: InvalidDefinitionException 이런 것이 뜸, 객체직렬화, @transient
    @GetMapping("/api/person/applies")
    public ResponseEntity<?> personApply() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Apply> applyList = applyService.getApplyList(1);

        return ResponseEntity.ok(new ApiUtil<>(applyList));
    }

    @GetMapping("/api/person/applies/{id}") // 내가 지원한 공고 디테일
    public ResponseEntity<?> personApply(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Apply apply = applyService.getPostDetail(sessionUser.getId(), id);

        return ResponseEntity.ok(new ApiUtil<>(apply));
    }

    @DeleteMapping("/api/person/applies/{id}")
    public ResponseEntity<?> appliedDelete(@PathVariable int id) {
        // applyService.findById(id); // 이걸 적었던 그때의 내가 이해가지 않지만 일단 주석처리해놓음
        applyService.deleteApply(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}