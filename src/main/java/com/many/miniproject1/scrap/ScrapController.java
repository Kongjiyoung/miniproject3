package com.many.miniproject1.scrap;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.apply.Apply;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.offer.Offer;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeService;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScrapController {
    private final HttpSession session;
    private final ScrapService scrapService;
    private final ResumeService resumeService;

    //개인 채용 공고 스크랩
    @GetMapping("/person/scraps")
    public ResponseEntity<?> personScrap() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Scrap> scrapList = scrapService.personScrapForm(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(scrapList));
    }

    @GetMapping("/person/scraps/{id}/detail")
    public ResponseEntity<?> personScrapDetailForm(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        //뷰내용 뿌리기
        Scrap scrap = scrapService.getScrapPostDetail(id);

        //이력서 선택
        List<Resume> resumeList = resumeService.getResumeFindBySessionUserId(sessionUser.getId());

        //resumeList도 같이 DTO에 담기
        return ResponseEntity.ok(new ApiUtil<>(scrap));
    }

    @PostMapping("/person/scraps/{id}/detail/delete")
    public ResponseEntity<?> personScrapDelete(@PathVariable Integer id) {
        scrapService.deleteScrapPost(id);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    @PostMapping("/person/scraps/{id}/detail/apply")
    public ResponseEntity<?> personPostApply(@PathVariable Integer id, Integer resumeChoice) { // 스크랩 아이디와 이력서 아이디를 받아서
        Apply apply=scrapService.saveApply(id, resumeChoice);
        return ResponseEntity.ok(new ApiUtil<>(apply));    }

    //기업 이력서 스크랩
    @GetMapping("/company/scraps")
    public ResponseEntity<?> companyScrapForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Scrap> scrapList = scrapService.companyScrapList(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(scrapList));
    }

    @GetMapping("/company/scraps/{id}")
    public ResponseEntity<?> companyScrapDetailForm(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Scrap scrap = scrapService.getResumeDetail(sessionUser.getId(), id);
        List<Post> postList = scrapService.companyPostList(sessionUser.getId());

        //postList도 같이 DTO에 담아서 넘기기
        return ResponseEntity.ok(new ApiUtil<>(scrap));
    }

    @DeleteMapping("/company/scraps/{id}")
    public ResponseEntity<?> companyScrapDelete(@PathVariable Integer id) {
        scrapService.deleteScrap(id);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    @PostMapping("/company/scraps/{id}")
    public ResponseEntity<?> companyResumeOffer(@PathVariable Integer id, Integer postChoice) {
        Offer offer = scrapService.sendPostToResume(id, postChoice);
        return ResponseEntity.ok(new ApiUtil<>(offer));
    }

}