package com.many.miniproject1.apply;


import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeRequest;
import com.many.miniproject1.resume.ResumeResponse;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApplyController {
    private final HttpSession session;
    private final ApplyRepository applyRepository;

    private final ApplyService applyService;

    //기업에서 받은 이력서 관리

    @GetMapping("/company/resumes")
    public String companyResume(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        return "company/applied-resumes";
    }

    @GetMapping("/company/resumes/{id}")
    public String companyResumeDetail(@PathVariable int id, HttpServletRequest request, ApplyResponse.CompanyResumeDTO respDTO) {
        Apply apply = applyService.findById(id);
        applyService.companyResumeDetail(respDTO);
        System.out.println("test:: " + apply);
        request.setAttribute("apply",apply);
        return "company/applied-resume-detail";
    }

    @PostMapping("/company/resumes/{id}/ispass")
    public String companyPass(@PathVariable int id, ApplyRequest.UpdateDTO request) {
//        비행기 버튼 누르고 나서 어디로 가야하는지 잘 모르겠어서 현재 페이지로 남겨놓음.
        return "redirect:/company/resumes/{id}";
    }

    // 개인이 지원한 이력서 목록 YSH
    @GetMapping("/person/applies")
    public String personApply(HttpServletRequest request) {
        User sessionUser=(User) session.getAttribute("sessionUser");
        List<Apply> applyList = applyService.getApplyList(sessionUser.getId());
        request.setAttribute("applyList", applyList);
        System.out.println(applyList);

        return "person/applies";
    }
    @GetMapping("/person/applies/{id}") // 내가 지원한 공고 디테일
    public String personApply(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Apply apply=applyService.getPostDetail(sessionUser.getId(), id);
        request.setAttribute("apply", apply);
        // 스킬 리스트 만들어서 돌리기
        return "person/post-apply-detail"; // 이것과 포스트 디테일과의 차이: 취소하기 버튼이 있냐, 스크랩 버튼이 있냐 차이
    }

    @PostMapping("/person/applies/{id}/delete")
    public String appliedDelete(@PathVariable int id) {
        return "redirect:/person/applies";
    }
}
