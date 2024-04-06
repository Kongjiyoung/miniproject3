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
    //회사 마이페이지 공고 관리
    @GetMapping("/api/company/my-page/posts")
    public ResponseEntity<?> companyPosts() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<PostResponse.PostListDTO> respDTO = postService.getResumeList(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/company/my-page/posts/{id}")
    public ResponseEntity<?> companyPostDetail(@PathVariable Integer id) {
        PostResponse.DetailDTO respDTO = postService.postDetail(id);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 이력서 저장
    @PostMapping("/api/company/my-page/posts")
    public ResponseEntity<?> companySavePost(@Valid @RequestBody PostRequest.SavePostDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        PostResponse.PostDTO respDTO = postService.save(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PutMapping("/api/company/my-page/posts/{id}")
    public ResponseEntity<?> companyUpdatePost(@PathVariable Integer id, @RequestBody PostRequest.UpdatePostDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        PostResponse.PostUpdateDTO respDTO = postService.updatePost(id, sessionUser.getId(), reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @DeleteMapping("/api/company/my-page/posts/{id}")
    public ResponseEntity<?> companyDeletePost(@PathVariable Integer id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        postService.postDelete(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}