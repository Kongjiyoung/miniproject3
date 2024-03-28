package com.many.miniproject1.post;

import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final HttpSession session;
    private final PostService postService;

    //회사 공고 관리
    @GetMapping("/company/posts")
    public String companyPosts(HttpServletRequest request) { // 이 페이지는 포스트들을 확인할 수 있는 페이지라 이름 변경했습니다.
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Post> postList =postService.getResumeList(sessionUser.getId());
        request.setAttribute("postlist", postList);
        return "company/posts";
    }

    // 공고 상세보기 YSH
    @GetMapping("/company/posts/{id}")
    public String companyPostDetailForm(HttpServletRequest request , PostResponse.PostDetailDTO responseDTO) {
        Post post = postService.postDetail(responseDTO);
        request.setAttribute("post",post);
        return "company/post-detail";
    }

    @GetMapping("/company/posts/save-form")
    public String companyPostForm() {
        return "company/post-save-form";
    }

    @PostMapping("/company/posts/save")
    public String companySavePost(PostRequest.PostSaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        postService.save(reqDTO, sessionUser);
        return "redirect:/company/posts";
    }

    @GetMapping("/company/posts/{id}/update-form")
    public String companyUpdatePostForm(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Post post = postService.findByPost(id);
        request.setAttribute("post", post);

        return "company/post-update-form";
    }

    @PostMapping("/company/posts/{id}/update")
    public String companyUpdatePost(@PathVariable int id, PostRequest.UpdatePostDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println(reqDTO);
        postService.updatePost(id, sessionUser.getId(), reqDTO);

        return "redirect:/company/posts/" + id;
    }

    @PostMapping("/company/posts/{id}/delete")
    public String companyDeletePost(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        postService.postDelete(id, sessionUser.getId());
        return "redirect:/company/posts";
    }
}