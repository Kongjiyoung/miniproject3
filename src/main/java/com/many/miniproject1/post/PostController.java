package com.many.miniproject1.post;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
        List<PostResponse.PostListDTO> respDTO =postService.getResumeList();
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/api/company/posts/{id}")
    public ResponseEntity<?> companyPostDetail(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        PostResponse.DetailDTO respDTO = postService.postDetail(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 이력서 저장
    @PostMapping("/api/company/posts")
    public ResponseEntity<?> companySavePost(@RequestBody PostRequest.PostSaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Post post = postService.save(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(post));
    }

    @PutMapping("/api/company/posts/{id}/update")
    public ResponseEntity<?> companyUpdatePost(@PathVariable int id, PostRequest.UpdatePostDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println(reqDTO);
        postService.updatePost(id, sessionUser.getId(), reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }

    @DeleteMapping("/api/company/posts/{id}")
    public ResponseEntity<?> companyDeletePost(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        postService.postDelete(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}