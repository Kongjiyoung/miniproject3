package com.many.miniproject1.apply;


import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.main.MainService;
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
    private final MainService mainService;

    ////////////////////////// 기업
    // 기업이 받은 이력서 목록
    @GetMapping("/api/company/my-page/receive-resumes")
    public ResponseEntity<?> companyReceives() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ApplyResponse.AppliedResumeSkillDTO> respDTO = applyService.getAppliedResumeSkillDTOs(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }


    // 기업이 받은 이력서 디테일
    @GetMapping("/api/company/my-page/receive-resumes/{id}") //@GetMapping("/api/company/resumes/{id}")

    public ResponseEntity<?> companyReceiveDetail(@PathVariable Integer id) {
        ApplyResponse.AppliedResumeSkillDetailDTO respDTO = applyService.getAppliedResume(id);
        applyService.companyResumeDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 지원 받은 이력서 합격 여부
    @PutMapping("/api/company/my-page/receive/{id}/is-pass")// @PutMapping("/api/company/resumes/{id}/is-pass")
    public ResponseEntity<?> companyPass(@PathVariable Integer id, @RequestBody ApplyRequest.UpdateIsPassDTO reqDTO) {
        ApplyResponse.UpdateIsPassDTO respDTO = applyService.isPassResume(id, reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    
    /////////////////////////////////개인
    // 개인이 지원한 공고 목록
    @GetMapping("/api/person/my-page/apply-posts")
    public ResponseEntity<?> personApplies() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ApplyResponse.ApplyPostSkillDTO> applyPostSkillDTOList = applyService.getApplyPostSkillDTOs(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(applyPostSkillDTOList));
    }

    // 개인이 지원한 공고 디테일
    @GetMapping("/api/person/my-page/apply-posts/{id}") // 내가 지원한 공고 디테일

    public ResponseEntity<?> personApply(@PathVariable int id) {
        ApplyResponse.ApplyPostSkillDetailDTO applyPostDetail = applyService.getPostDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(applyPostDetail));
    }

    // 메인 화면에 게시된 공고에 지원하기
    @PostMapping("/api/person/main/posts/{id}/apply")
    public ResponseEntity<?> personMainApply(@PathVariable Integer id, @RequestBody ApplyRequest.ResumeChoiceDTO resumeChoice) {
        ApplyResponse.ApplyDTO respDTO = applyService.saveApplyByMain(id, resumeChoice.getResumeChoice());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 스크랩한 공고에 지원하기
    @PostMapping("/api/person/my-page/scraps/{id}/apply")
    public ResponseEntity<?> companyResumeOffer(@PathVariable Integer id, @RequestBody ApplyRequest.ResumeChoiceDTO resumeChoice) {
        ApplyResponse.ApplyDTO respDTO = applyService.saveApplyByScrap(id, resumeChoice.getResumeChoice());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 지원 철회하기
    @DeleteMapping("/api/person/my-page/apply-posts/{id}")
    public ResponseEntity<?> applyDelete(@PathVariable Integer id) {
        applyService.deleteApply(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}