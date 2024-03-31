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

    //회사 공고 관리
    @GetMapping("/company/posts")
    public ResponseEntity<?> companyPosts(HttpServletRequest request) { // 이 페이지는 포스트들을 확인할 수 있는 페이지라 이름 변경했습니다.
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Post> postList =postService.getResumeList(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(postList));
    }

    @GetMapping("/company/posts/{id}")
    public ResponseEntity<?> companyPostDetailForm(HttpServletRequest request, @PathVariable Integer id) {
        Post post = postService.postDetail(id);
        request.setAttribute("post",post);
        return ResponseEntity.ok(new ApiUtil<>(post));
    }

    // 이력서 저장
    @PostMapping("/company/posts/save")
    public ResponseEntity<?> companySavePost(PostRequest.PostSaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        postService.save(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }


    @PostMapping("/company/posts/{id}/update")
    public ResponseEntity<?> companyUpdatePost(@PathVariable int id, PostRequest.UpdatePostDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println(reqDTO);
        postService.updatePost(id, sessionUser.getId(), reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }

    @DeleteMapping("/company/posts/{id}/delete")
    public ResponseEntity<?> companyDeletePost(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        postService.postDelete(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}