package com.many.miniproject1.scrap;

import com.many.miniproject1.apply.Apply;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.offer.Offer;
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
public class ScrapController {
    private final HttpSession session;
    private final ScrapService scrapService;

    //개인 채용 공고 스크랩
    @GetMapping("/person/scrap")
    public String personScrapForm(HttpServletRequest request) {
        User sessionUser=(User) session.getAttribute("sessionUser");
        List<Scrap> scrapList = scrapService.personScrapForm(sessionUser.getId());
        request.setAttribute("scrapList", scrapList);
        System.out.println(scrapList);

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
        scrapService.deleteScrapPost(id);
        return "redirect:/person/scrap";
    }

    @PostMapping("/person/scrap/{id}/detail/apply")
    public String personPostApply(@PathVariable int id, int reusmeId) {
        Apply apply=scrapService.saveApply(id,reusmeId);
        return "redirect:/person/scrap/{id}/detail";
    }
    //기업 이력서 스크랩
    @GetMapping("/company/scrap")
    public String companyScrapForm(HttpServletRequest request) {
        User sessionUser=(User) session.getAttribute("sessionUser");
        List<Scrap> scrapList = scrapService.companyScrapList(sessionUser.getId());
        request.setAttribute("scrapList", scrapList);
        return "company/scrap";
    }

    @GetMapping("/company/scrap/{id}/detail")
    public String companyScrapDetailForm(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Scrap scrap = scrapService.getResumeDetail(sessionUser.getId(), id);
        List<Post> postList = scrapService.companyPostList(sessionUser.getId());
        request.setAttribute("postList",postList);
        request.setAttribute("scrap", scrap);
        return "company/resume-scrap-detail";
    }

    @PostMapping("/company/scrap/{id}/detail/delete")
    public String companyScrapDelete(@PathVariable Integer id) {
        scrapService.deleteScrap(id);
        return "redirect:/company/scrap";
    }

    @PostMapping("/company/scrap/{id}/detail/offer")
    public String companyResumeOffer(@PathVariable Integer id, Integer postChoice) {
        Offer offer = scrapService.sendPostToResume(id, postChoice);
        return "redirect:/company/scrap/{id}/detail";
    }

}