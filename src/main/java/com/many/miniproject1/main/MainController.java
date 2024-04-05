package com.many.miniproject1.main;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.apply.ApplyResponse;
import com.many.miniproject1.offer.OfferRequest;
import com.many.miniproject1.resume.ResumeJPARepository;
import com.many.miniproject1.scrap.ScrapResponse;
import com.many.miniproject1.user.SessionUser;
import com.many.miniproject1.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class MainController {
    private final HttpSession session;
    private final MainService mainService;
    private final UserService userService;
    private final ResumeJPARepository resumeJPARepository;

    //메인 이력서 목록
    @GetMapping("/resumes")
    public ResponseEntity<?> resumes() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<MainResponse.MainResumesDTO> respDTO = mainService.mainResumes();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //메인 이력서 디테일
    @GetMapping("/resumes/{id}")
    public ResponseEntity<?> mainResumeDetail(@PathVariable Integer id) {

        // 현재 로그인한 사용자가 회사인 경우에만 해당 회사가 작성한 채용 공고 목록 가져오기
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<MainResponse.PostTitleListDTO> postTitleListDTOList = new ArrayList<>();
        boolean isCompany = false;
        if (sessionUser != null) {
            String role = sessionUser.getRole();
            if (role.equals("company")) {
                isCompany = true;
            }
            Integer companyId = sessionUser.getId();
            postTitleListDTOList = mainService.getPostTitleListDTOs(sessionUser.getId(), companyId); // 세션유저의 아이디와 컴퍼니 아이디가 일치해야 정보가 넘어감

        }
        // TODO: 테스트 끝나고 바로 아래 한 줄의 코드 삭제. 세션유저의 아이디와 컴퍼니 아이디가 일치해야 정보가 넘어가서 테스트할 때 주석 해제하고 보라고 빼놓음. 테스트할때 14, 14 넣으면 됨.
       // postTitleListDTOList = mainService.getPostTitleListDTOs(14, 14);

        MainResponse.MainResumeDetailDTO mainResumeDetailDTO = mainService.getResumeDetail(id);
        //resume만 아니라 postList도 같이 넘겨야함
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("postTitleListDTOList", postTitleListDTOList);
        responseBody.put("mainResumeDetailDTO", mainResumeDetailDTO);
        return ResponseEntity.ok(new ApiUtil<>(responseBody));
    }

    @PostMapping("/api/resumes/{id}/offer")
    public ResponseEntity<?> companyResumeOffer(@PathVariable Integer id, @RequestBody OfferRequest.MainOfferSaveDTO reqDTO) {
        reqDTO = mainService.sendPostToResume(id, reqDTO.getPostId());

        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }

    @PostMapping("/api/resumes/{id}/scrap")
    public ResponseEntity<?> companyResumeScrap(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ScrapResponse.MainResumeScrapDTO respDTO = mainService.resumeScrap(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //메인 채용 공고
    @GetMapping({"/posts", "/"})
    public ResponseEntity<?> posts() {
        List<MainResponse.MainPostsDTO> respDTO = mainService.getPostList();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //메인 채용 공고 디테일
    @GetMapping("/api/posts/{id}")
    public ResponseEntity<?> postDetail(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        // 목적: 로그인 하지 않아도 채용 공고가 보임, 개인일때 resume제목을 주고 선택하여 지원함.
        boolean isPerson = false;
        if (sessionUser != null) {
            if (sessionUser.getRole().equals("person")) {
                isPerson=true;
                MainResponse.PostDetailDTO respDTO = mainService.getPostIsCompanyDetail(id, sessionUser.getId(), isPerson);
                return ResponseEntity.ok(new ApiUtil<>(respDTO));
            }
        }
        MainResponse.PostDetailDTO respDTO = mainService.getPostDetail(id, isPerson);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //이력서 지원하기
    @PostMapping("/api/posts/{id}/apply")
    public ResponseEntity<?> personPostApply(@PathVariable Integer id, @RequestBody MainRequest.ResumeChoiceDTO resumeChoice) {
        ApplyResponse.PostApplyDTO respDTO = mainService.personPostApply(id, resumeChoice.getResumeChoice());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));

    }

    //공고 스크랩하기
    @PostMapping("/api/posts/{id}/scrap")
    public ResponseEntity<?> personPostScrap(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        ScrapResponse.PostScrapSaveDTO respDTO = mainService.personPostScrap(sessionUser.getId(), id);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));

    }

    //메인 매칭 이력서 목록
    @GetMapping("/api/posts/matching")
    public ResponseEntity<?> matchingPosts() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<MainResponse.PostMatchingChoiceDTO> postList = mainService.findByUserIdPost(sessionUser.getId());
        Integer postChoice = (Integer) session.getAttribute("postChoice");
        if (postChoice != null) {
            List<MainResponse.MainPostMatchDTO> respDTO = mainService.matchingResume(postChoice);
            //resumeList와 posts 와 DTO담아서 넘ㄱ기기
            return ResponseEntity.ok(new ApiUtil<>(respDTO, postList));
        }

        return ResponseEntity.ok(new ApiUtil<>(postList));
    }

    //공고 선택하여 매칭하기
    @PostMapping("/api/posts/match")
    public ResponseEntity<?> matchingPost(@RequestBody MainRequest.PostChoiceDTO postChoiceDTO) {
        session.setAttribute("postChoice", postChoiceDTO.getPostChoice());
        int respDTO = postChoiceDTO.getPostChoice();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));

    }

    //메인 매칭 공고 목록
    @GetMapping("/api/resumes/matching")
    public ResponseEntity<?> matchingResumes() {
        //공고 가져오기
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<MainResponse.ResumeMatchingChoiceDTO> resumeList = mainService.findByUserIdResume(sessionUser.getId());
        Integer resumeChoice = (Integer) session.getAttribute("resumeChoice");
        if (resumeChoice != null) {
            List<MainResponse.MainResumeMatchDTO> postList = mainService.matchingPost(resumeChoice);
            //resumeList와 함께 DTO에 담기
            return ResponseEntity.ok(new ApiUtil<>(resumeList, postList));
        }

        return ResponseEntity.ok(new ApiUtil<>(resumeList));

    }

    //이력서 선택하여 매칭하기
    @PostMapping("/api/resumes/match")
    public ResponseEntity<?> matchingResume(@RequestBody MainRequest.ResumeChoiceDTO resumeChoiceDTO) {
        session.setAttribute("resumeChoice", resumeChoiceDTO.getResumeChoice());
        int respDTO = resumeChoiceDTO.getResumeChoice();
        return ResponseEntity.ok(new ApiUtil<>(respDTO));

    }
}



