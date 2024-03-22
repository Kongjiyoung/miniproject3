package com.many.miniproject1.backup.apply;

import com.many.miniproject1.backup.resume.ResumeRepository;
import com.many.miniproject1.backup.skill.SkillRepository;
import com.many.miniproject1.backup.post.PostRepository;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ApplyController {
    private final HttpSession session;
    private final ApplyRepository applyRepository;
    private final SkillRepository skillRepository;
    private final PostRepository postRepository;
    private final ResumeRepository resumeRepository;
    //기업에서 받은 이력서 관리

    @GetMapping("/company/resume")
    public String companyResume(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println(sessionUser.getId());

        return "company/company-resumes";
    }

    @GetMapping("/company/resume/{id}/detail")
    public String companyResumeDetail(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        return "company/resume-apply-detail";
    }

    @PostMapping("/company/resume/{id}/detail/ispass")
    public String companyPass(@PathVariable int id, ApplyRequest.UpdateDTO request) {

        return "redirect:/company/resume/{id}/detail";
    }

    //이력서 현황
    @GetMapping("/person/apply")
    public String personApply(HttpServletRequest request) {
        User sessionUser=(User) session.getAttribute("sessionUser");

        return "person/applies";
    }
    @GetMapping("/person/apply/{id}/detail")
    public String personApply(@PathVariable int id, HttpServletRequest request) {
        // 목적: 포스트 디테일 페이지를 불러온다.(0)
        // 1. 로그인 하지 않은 유저 로그인의 길로 인도
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 스킬 리스트 만들어서 돌리기
        return "post-apply-detail";
    }

    @PostMapping("/person/apply/{id}/delete")
    public String appliedDelete(@PathVariable int id) {
        System.out.println(id);
        applyRepository.applieddelete(id);
        return "redirect:/person/apply";
    }

}
