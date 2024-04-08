package com.many.miniproject1.scrap;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.user.SessionUser;
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

    ///////////////////// 기업
    // 스크랩한 이력서 목록
    @GetMapping("/api/company/my-page/scraps")
    public ResponseEntity<?> companyScraps() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ScrapResponse.ScrapResumeListDTO> respDTO = scrapService.companyScrapList(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 스크랩한 이력서 디테일
    @GetMapping("/api/company/my-page/scraps/{id}")
    public ResponseEntity<?> companyScrapDetail(@PathVariable Integer id) {
        ScrapResponse.ScrapResumeDetailDTO respDTO = scrapService.getResumeDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 이력서 스크랩하기
    @PostMapping({"/api/main/resumes/{id}/scrap"})
    public ResponseEntity<?> companyResumeScrap(@PathVariable Integer id, @RequestBody ScrapRequest.ScrapResumeDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ScrapResponse.MainResumeScrapDTO respDTO = scrapService.resumeScrap(id, sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 스크랩한 이력서 삭제
    @DeleteMapping("/api/company/my-page/scraps/{id}")
    public ResponseEntity<?> companyScrapDelete(@PathVariable Integer id) {
        scrapService.deleteScrap(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    ///////////////////// 개인
    // 스크랩한 공고 목록
    @GetMapping("/api/person/my-page/scraps")
    public ResponseEntity<?> personScrap() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ScrapResponse.ScrapPostListDTO> respDTO = scrapService.personScrapList(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 스크랩한 공고 디테일
    @GetMapping("api/person/my-page/scraps/{id}")
    public ResponseEntity<?> personScrapDetailForm(@PathVariable Integer id) {
        ScrapResponse.ScrapPostDetailDTO respDTO = scrapService.ScrapPostDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 공고 스크랩하기
    @PostMapping("/api/main/posts/{id}/scrap")
    public ResponseEntity<?> personPostScrap(@PathVariable Integer id, @RequestBody ScrapRequest.ScrapPostDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ScrapResponse.MainPostScrapDTO respDTO = scrapService.postScrap(id, sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 스크랩한 공고 삭제
    @DeleteMapping("/api/person/my-page/scraps/{id}")
    public ResponseEntity<?> personScrapDelete(@PathVariable Integer id) {
        scrapService.deleteScrapPost(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}