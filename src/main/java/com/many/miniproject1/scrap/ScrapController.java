package com.many.miniproject1.scrap;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.apply.Apply;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.offer.Offer;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeService;
import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserResponse;
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
    @GetMapping("/api/company/scraps")
    public ResponseEntity<?> companyScraps() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ScrapResponse.ScrapResumeListDTO> respDTO = scrapService.companyScrapList(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/company/scraps/{id}")
    public ResponseEntity<?> companyScrapDetail(@PathVariable Integer id) {
        ScrapResponse.ScrapResumeDetailDTO respDTO = scrapService.getResumeDetail(id);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @DeleteMapping("/api/company/scraps/{id}")
    public ResponseEntity<?> companyScrapDelete(@PathVariable Integer id) {
        scrapService.deleteScrap(id);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    @PostMapping("/api/company/scraps")
    public ResponseEntity<?> companyResumeOffer(@PathVariable Integer id, Integer postChoice) {
        Offer offer = scrapService.sendPostToResume(id, postChoice);
        return ResponseEntity.ok(new ApiUtil<>(offer));
    }

}