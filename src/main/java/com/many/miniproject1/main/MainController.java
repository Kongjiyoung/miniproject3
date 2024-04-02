package com.many.miniproject1.main;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.apply.ApplyResponse;
import com.many.miniproject1.offer.OfferRequest;
import com.many.miniproject1.scrap.Scrap;
import com.many.miniproject1.scrap.ScrapResponse;
import com.many.miniproject1.user.User;
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


    //맞춤 공고 - 기업이 보는 매칭 이력서

    /**
     * TODO: company/matching의 검색 버튼을 누르면 스트링을 인터저로 변환하지 못 해서 생기는 에러가 뜨는데 로직을 날려서 그런 것인지 원래 있던 문제인지 몰라서 남겨둠.
     *  그 문제는 company/match로 넘어가는 과정에서 터지는 것이다.
     *  /person/matching도 마찬가지이니 담당자는 반드시 체크할 것!!!
     */


    //메인 구직 공고
    // 04-02 YSH
    @GetMapping("/resumes")
    public ResponseEntity<?> mainResumes() {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<MainResponse.mainResumesDTO> respDTO = mainService.mainResumes();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/resumes/{id}")
    public ResponseEntity<?> mainResumeDetail(@PathVariable Integer id) {

        // 현재 로그인한 사용자가 회사인 경우에만 해당 회사가 작성한 채용 공고 목록 가져오기
        User sessionUser = (User) session.getAttribute("sessionUser");
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
        //postTitleListDTOList = mainService.getPostTitleListDTOs(14, 14);

        MainResponse.MainResumeDetailDTO mainResumeDetailDTO = mainService.getResumeDetail(id);
        //resume만 아니라 postList도 같이 넘겨야함
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("postTitleListDTOList", postTitleListDTOList);
        responseBody.put("mainResumeDetailDTO", mainResumeDetailDTO);
        System.out.println("responseBody: " + responseBody);
        return ResponseEntity.ok(new ApiUtil<>(responseBody));
    }

    @PostMapping("/resumes/{id}/offer")
    public ResponseEntity<?> companyResumeOffer(@PathVariable Integer id, @RequestBody OfferRequest.MainOfferSaveDTO reqDTO) {
        // 회사가 개인의 이력서를 보고 맘에 들면 오퍼를 보냄
        // INSERT INTO offer_tb(resume_id, post_id, created_at) VALUES (1, 1, now());
        reqDTO = mainService.sendPostToResume(id, reqDTO.getPostId()); // 해당 아이디의 이력서로 포스트를 선택해서 오퍼를 보냄

        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }

    // 04-02 YSH
    @PostMapping("/resumes/{id}/scrap")
    public ResponseEntity<?> companyResumeScrap(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        ScrapResponse.MainResumeScrapDTO respDTO = mainService.resumeScrap(id, sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    //메인 채용 공고
    @GetMapping({"/posts", "/"})
    public ResponseEntity<?> posts() {
        List<MainResponse.mainPostsDTO> respDTO = mainService.getPostList();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> postDetail(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 목적: 로그인 하지 않아도 회사에서 올린 공고가 보임
        MainResponse.PostDetailDTO respDTO = mainService.getPostDetail(id);
        if (sessionUser != null) {
            if (sessionUser.getRole().equals("person")) {
                List<MainResponse.ApplyListDTO> resumeList = mainService.getResumeId(sessionUser.getId());
                return ResponseEntity.ok(new ApiUtil<>(respDTO, resumeList));
            }
        }
        //resumeList도 같이 dto에 담아서 넘길 예정
        return ResponseEntity.ok(new ApiUtil<>(respDTO));

    }

    // 지원하기 버튼 안 보임
    @PostMapping("/posts/{id}/apply")
    public ResponseEntity<?> personPostApply(@PathVariable int id, @RequestBody MainRequest.ResumeChoiceDTO resumeChoice) {
        System.out.println("resumeChoice = " + resumeChoice);
        ApplyResponse.PostApplyDTO respDTO = mainService.personPostApply(id, resumeChoice.getResumeChoice());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));

    }

    @PostMapping("/posts/{id}/scrap")
    public ResponseEntity<?> personPostScrap(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        ScrapResponse.PostScrapSaveDTO respDTO = mainService.personPostScrap(sessionUser.getId(), id);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));

    }


    @GetMapping("/posts/matching")
    public ResponseEntity<?> matchingPosts() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<MainResponse.PosteMatchingChoiceDTO> postList = mainService.findByUserIdPost(sessionUser.getId());
        Integer postChoice = (Integer) session.getAttribute("postChoice");
        if (postChoice != null) {
            List<MainResponse.MainPostMatchDTO> respDTO = mainService.matchingResume(postChoice);
            //resumeList와 posts 와 DTO담아서 넘ㄱ기기
            return ResponseEntity.ok(new ApiUtil<>(respDTO, postList));
        }
        //
        return ResponseEntity.ok(new ApiUtil<>(postList));


    }

    @PostMapping("/posts/match")
    public ResponseEntity<?> matchingPost(@RequestBody MainRequest.PostChoiceDTO postChoiceDTO) {
        session.setAttribute("postChoice", postChoiceDTO.getPostChoice());
        int respDTO = postChoiceDTO.getPostChoice();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));

    }

    //맞춤 공고 - 개인이 보는 매칭 공고
    @GetMapping("/resumes/matching")
    public ResponseEntity<?> matchingResumes() {
        //공고 가져오기
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<MainResponse.ResumeeMatchingChoiceDTO> resumeList = mainService.findByUserIdResume(sessionUser.getId());
        Integer resumeChoice = (Integer) session.getAttribute("resumeChoice");
        if (resumeChoice != null) {
            List<MainResponse.MainResumeMatchDTO> postList = mainService.matchingPost(resumeChoice);
            //resumeList와 함께 DTO에 담기
            return ResponseEntity.ok(new ApiUtil<>(resumeList, postList));
        }

        return ResponseEntity.ok(new ApiUtil<>(resumeList));

    }

    @PostMapping("/resumes/match")
    public ResponseEntity<?> matchingResume(@RequestBody MainRequest.ResumeChoiceDTO resumeChoiceDTO) {
        session.setAttribute("resumeChoice", resumeChoiceDTO.getResumeChoice());
        int respDTO = resumeChoiceDTO.getResumeChoice();
        return ResponseEntity.ok(new ApiUtil<>(respDTO));

    }
}



