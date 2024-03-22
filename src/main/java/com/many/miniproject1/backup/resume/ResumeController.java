package com.many.miniproject1.backup.resume;

import com.many.miniproject1.backup.skill.SkillRepository;
import com.many.miniproject1.backup.skill.SkillRequest;
import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserFileService;
import com.many.miniproject1.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ResumeController{
    private final ResumeRepository resumeRepository;
    private final SkillRepository skillRepository;
    private final HttpSession session;
    private final UserFileService userFileService;
    private final UserRepository userRepository;

    //개인 이력서 관리
    @GetMapping("/person/resume")
    public String personResumeForm(HttpServletRequest request) {
        return "person/resumes";
    }

    @GetMapping("/person/resume/{id}/detail")
    public String personResumeDetailForm(@PathVariable int id, HttpServletRequest request) {
        return "person/resume-detail";
    }

    @GetMapping("/person/resume/save-form")
    public String personSaveResumeForm(ResumeRequest.SaveDTO requestDTO, HttpServletRequest request) {
        return "person/resume-save-form";
    }

    @PostMapping("/person/resume/save")
    public String personSaveResume(ResumeRequest.SaveDTO requestDTO, HttpServletRequest request, @RequestParam("skills") List<String> skills) {
        return "redirect:/person/resume";
    }

    @GetMapping("/person/resume/detail/{id}/update-form")
    public String personUpdateResumeForm(@PathVariable int id, HttpServletRequest request) {
        return "person/resume-update-form";
    }

    @PostMapping("/person/resume/{id}/detail/update")
    public String personUpdateResume(@PathVariable int id, ResumeRequest.UpdateDTO requestDTO, HttpServletRequest request, @RequestParam("skill") List<String> skills) {
        return "redirect:/person/resume/" + id + "/detail";

    }

    @PostMapping("/person/resume/detail/{id}/delete")
    public String personDeletePost(@PathVariable int id, HttpServletRequest request) {
        return "redirect:/person/resume";
    }
}