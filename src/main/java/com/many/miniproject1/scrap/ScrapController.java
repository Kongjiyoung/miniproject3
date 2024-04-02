package com.many.miniproject1.scrap;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.apply.Apply;
import com.many.miniproject1.apply.ApplyResponse;
import com.many.miniproject1.offer.OfferResponse;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.offer.Offer;
import com.many.miniproject1.post.PostResponse;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeService;
import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScrapController {
    private final HttpSession session;
    private final ScrapService scrapService;
    private final ResumeService resumeService;

    //개인 채용 공고 스크랩
    @GetMapping("/api/person/scraps")
    public ResponseEntity<?> personScrap() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ScrapResponse.ScrapPostListDTO> respDTO = scrapService.personScrapList(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/person/scraps/{id}/detail")
    public ResponseEntity<?> personScrapDetailForm(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        //뷰내용 뿌리기
        Scrap scrap = scrapService.getScrapPostDetail(id);

        //이력서 선택
        List<Resume> resumeList = resumeService.getResumeFindBySessionUserId(sessionUser.getId());
        ScrapResponse.ScrapPostDetailDTO respDTO = scrapService.ScrapPostDetail(id, sessionUser);

        //resumeList도 같이 DTO에 담기
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @DeleteMapping("/api/person/scraps/{id}")
    public ResponseEntity<?> personScrapDelete(@PathVariable Integer id) {
        scrapService.deleteScrapPost(id);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    @PostMapping("/api/person/scraps/{id}")
    public ResponseEntity<?> personPostApply(@PathVariable Integer id, @RequestBody ScrapRequest.ResumeChoiceDTO resumeChoice) { // 스크랩 아이디와 이력서 아이디를 받아서
        System.out.println("resumeChoice =" + resumeChoice);
        ApplyResponse.PostApplyDTO respDTO =scrapService.saveApply(id, resumeChoice.getResumeChoice());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));

    }


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

    @PostMapping("/api/company/scraps/{id}")
    public ResponseEntity<?> companyResumeOffer(@PathVariable Integer id, @RequestBody ScrapRequest.PostChoiceDTO postChoice) {
        OfferResponse.ChoiceDTO respDTO = scrapService.sendPostToResume(id, postChoice.getPostChoice());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

}