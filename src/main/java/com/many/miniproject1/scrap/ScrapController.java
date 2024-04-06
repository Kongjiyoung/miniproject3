package com.many.miniproject1.scrap;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.apply.ApplyResponse;
import com.many.miniproject1.main.MainService;
import com.many.miniproject1.resume.ResumeService;
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
    private final ResumeService resumeService;
    private final MainService mainService;

    //개인 채용 공고 스크랩

    @GetMapping("/api/person/my-page/scraps")
    public ResponseEntity<?> personScrap() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ScrapResponse.ScrapPostListDTO> respDTO = scrapService.personScrapList(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // TODO: 이거 컴퍼니 아이디 말고 스크랩 아이디로 다시 연결    "msg": "스크랩한 공고를 찾을 수 없습니다",
    @GetMapping("/api/person/my-page/scraps/{id}")
    public ResponseEntity<?> personScrapDetailForm(@PathVariable Integer id) {
        ScrapResponse.ScrapPostDetailDTO respDTO = scrapService.ScrapPostDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @DeleteMapping("/api/person/my-page/scraps/{id}")
    public ResponseEntity<?> personScrapDelete(@PathVariable Integer id) {
        scrapService.deleteScrapPost(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // TODO: 이거 컴퍼니 아이디 말고 스크랩 아이디로 다시 연결
    @PostMapping("/api/person/my-page/scraps")
    public ResponseEntity<?> personPostApply(@RequestBody ScrapRequest.ResumeChoiceDTO reqDTO) { // 스크랩 아이디와 이력서 아이디를 받아서
        ApplyResponse.PostApplyDTO respDTO = scrapService.saveApply(reqDTO.getId(), reqDTO.getResumeChoice());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //기업 이력서 스크랩
    @GetMapping("/api/company/my-page/scraps")
    public ResponseEntity<?> companyScraps() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<ScrapResponse.ScrapResumeListDTO> respDTO = scrapService.companyScrapList(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/company/my-page/scraps/{id}")
    public ResponseEntity<?> companyScrapDetail(@PathVariable Integer id) {
        ScrapResponse.ScrapResumeDetailDTO respDTO = scrapService.getResumeDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // TODO: 모든 스크랩들의 아이디 확인
    @DeleteMapping("/api/company/my-page/scraps/{id}")
    public ResponseEntity<?> companyScrapDelete(@PathVariable Integer id) {
        scrapService.deleteScrap(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

//    @PostMapping("/api/company/offers") // @PostMapping("/api/company/scraps/{id}")
//    public ResponseEntity<?> companyOfferToResume(@RequestBody ScrapRequest.PostChoiceDTO reqDTO) {
//        OfferResponse.ChoiceDTO respDTO = scrapService.sendPostToResume(reqD, reqDTO.getPostChoice());
//        return ResponseEntity.ok(new ApiUtil<>(respDTO));
//    }

    ///////////////////////////////////// 추가됨

    // TODO: 일단 메인 서비스 연결, 수정해야함.
    // 개인이 공고 스크랩하기

    @PostMapping("/api/company/my-page/scraps") // @PostMapping("/api/resumes/{id}/scrap")
    public ResponseEntity<?> companyResumeScrap(@RequestBody ScrapRequest.ScrapResumeDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ScrapResponse.MainResumeScrapDTO respDTO = mainService.resumeScrap(reqDTO.getId(), sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 회사가 개인의 이력서를 스크랩
    // TODO: 포스트인데 리퀘스트 바디가 없음
    @PostMapping("/api/main/posts/{id}/scrap")  // 메인 전용 스크랩이 필요할 것인가!!
    public ResponseEntity<?> personMainScrap(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ScrapResponse.PostScrapSaveDTO respDTO = mainService.personPostScrap(sessionUser.getId(), id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}