package com.many.miniproject1.post;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final HttpSession session;
    private final PostService postService;

    // TODO : 이력서 조회 API 필요. update-form < findByPost(id)
    //회사 공고 관리
    @GetMapping("/api/company/posts")
    public ResponseEntity<?> companyPosts() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<PostResponse.CompanyPostsDTO> respDTO = postService.getResumeList(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/company/posts/{id}")
    public ResponseEntity<?> companyPostDetail(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        PostResponse.CompanyPostDetailDTO respDTO = postService.postDetail(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 이력서 저장
    @PostMapping("/api/company/posts")
    public ResponseEntity<?> companySavePost(@Valid @RequestBody PostRequest.PostSaveDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        PostResponse.SavePostDTO respDTO = postService.save(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PutMapping("/api/company/posts/{id}")
    public ResponseEntity<?> companyUpdatePost(@PathVariable Integer id, @RequestBody PostRequest.UpdatePostDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        PostResponse.PostUpdateDTO respDTO = postService.updatePost(id, sessionUser.getId(), reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @DeleteMapping("/api/company/posts/{id}")
    public ResponseEntity<?> companyDeletePost(@PathVariable int id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        postService.deletePost(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}