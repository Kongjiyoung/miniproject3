package com.many.miniproject1.backup.post;

import com.many.miniproject1.backup.skill.Skill;
import com.many.miniproject1.backup.skill.SkillRepository;
import com.many.miniproject1.backup.skill.SkillRequest;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final SkillRepository skillRepository;
    private final HttpSession session;

    //회사 공고 관리
    @GetMapping("/company/posts")
    public String companyPosts(HttpServletRequest request, Skill skill) {
        return "company/posts";
    }

    @GetMapping("/company/posts/detail/{id}") // 포스트 디테일 페이지 보기
    public String companyPostDetailForm(@PathVariable int id, HttpServletRequest request) {

        return "company/postDetail";
    }

    @GetMapping("/company/post/saveForm")
    public String companyPostForm(PostRequest.SaveDTO requestDTO, HttpServletRequest request) {

        return "company/savePostForm";
    }

    @PostMapping("/company/post/save")
    public String companySavePost(PostRequest.SaveDTO requestDTO, HttpServletRequest request, @RequestParam("skill") List<String> skills) {

        return "redirect:/company/post";
    }

    @GetMapping("/company/post/detail/{id}/updateForm")
    public String companyUpdatePostForm(@PathVariable int id, HttpServletRequest request) {

        return "company/updatePostForm";
    }

    @PostMapping("/company/post/detail/{id}/update")
    public String companyUpdatePost(@PathVariable int id, PostRequest.UpdateDTO requestDTO, HttpServletRequest request, @RequestParam("skill") List<String> skills) {

        return "redirect:/company/post/detail/" + id;
    }

    @PostMapping("/company/post/detail/{id}/delete")
    public String companyDeletePost(@PathVariable int id, HttpServletRequest request) {

        return "redirect:/company/post";
    }
}