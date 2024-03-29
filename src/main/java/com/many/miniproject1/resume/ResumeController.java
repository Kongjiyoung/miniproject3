package com.many.miniproject1.resume;

import com.many.miniproject1._core.errors.exception.Exception404;

import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final HttpSession session;

    //개인 이력서 관리
    @GetMapping("/person/resume")
    public String personResumeForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Resume> resumeList = resumeService.findResumeList(sessionUser.getId());
        request.setAttribute("resumeList",resumeList);
        return "person/resumes";
    }

    @GetMapping("/person/resume/{id}/detail")
    public String personResumeDetailForm(@PathVariable int id, HttpServletRequest request) {
        Resume resume = resumeService.getResumeDetail(id);
        request.setAttribute("resume", resume);
        return "person/resume-detail";
    }

    @GetMapping("/person/resume/save-form")
    public String personSaveResumeForm() {
        return "person/resume-save-form";
    }

    @PostMapping("/person/resume/save")
    public String personSaveResume(ResumeRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        //나중에 findById 해서 그 user 넣어야하는 거 아녀? 일단은 이렇게 둠
        System.out.println("requestDTO = " + requestDTO);
        Resume resume = resumeService.save(requestDTO, sessionUser);
        return "redirect:/person/resume";
    }

    @GetMapping("/person/resume/detail/{id}/update-form")
    public String personUpdateResumeForm(@PathVariable int id, HttpServletRequest request) {
        Resume resume = resumeService.findByResume(id);
        request.setAttribute("resume", resume);
        return "person/resume-update-form";
    }

    @PostMapping("/person/resume/{id}/detail/update")
    public String personUpdateResume(@PathVariable int id, ResumeRequest.UpdateDTO requestDTO) {
        System.out.println("requestDTO = " + requestDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.update(id, requestDTO);
        return "redirect:/person/resume/" + id + "/detail";
    }

    @PostMapping("/person/resume/{id}/delete")
    public String personDeleteResume(@PathVariable Integer id) {
        resumeService.deleteResume(id);
        return "redirect:/person/resume";
    }
}