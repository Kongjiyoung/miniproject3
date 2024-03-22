package com.many.miniproject1.backup.scrap;

import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ScrapController {
    private final HttpSession session;


    //개인 채용 공고 스크랩
    @GetMapping("/person/scrap")
    public String personScrapForm(HttpServletRequest request) {
        User sessionUser=(User) session.getAttribute("sessionUser");

        return "person/scrap";
    }
    @GetMapping("/person/scrap/{id}/detail")
    public String personScrapDetailForm(@PathVariable int id, HttpServletRequest request) {
        //뷰내용 뿌리기
        //이력서 선택
        return "person/post-scrap-detail";
    }

    @PostMapping("/person/scrap/{id}/detail/delete")
    public String personScrapDelete(@PathVariable int id) {
        User sessionUser=(User) session.getAttribute("sessionUser");

        return "redirect:/person/scrap";
    }

    @PostMapping("/person/scrap/{id}/detail/apply")
    public String personPostApply(@PathVariable int id, @RequestParam("resumeChoice") Integer resumeChoice) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        return "redirect:/person/scrap/{id}/detail";
    }
    //기업 이력서 스크랩
    @GetMapping("/company/scrap")
    public String companyScrapForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        return "company/scrap";
    }

    @GetMapping("/company/scrap/{id}/detail")
    public String companyScrapDetailForm(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        return "resume-scrap-detail";
    }

    @PostMapping("/company/scrap/{id}/detail/delete")
    public String companyScrapDelete(@PathVariable int id) {
        User sessionUser=(User) session.getAttribute("sessionUser");

        return "redirect:/company/scrap";
    }

    @PostMapping("/company/scrap/{id}/detail/offer")
    public String companyResumeOffer(@PathVariable int id, @RequestParam("postChoice") Integer postChoice) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        return "redirect:/company/scrap/{id}/detail";
    }

}