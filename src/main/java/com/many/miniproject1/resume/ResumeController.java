package com.many.miniproject1.resume;

import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillRepository;
import com.many.miniproject1.skill.SkillRequest;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeRepository resumeRepository;
    private final SkillRepository skillRepository;
    private final HttpSession session;

    //개인 이력서 관리
    @GetMapping("/person/resume")
    public String personResumeForm(HttpServletRequest request, Skill skill) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/person/loginForm";
        }

        List<ResumeResponse.DetailDTO> resumeList = resumeRepository.findResume(sessionUser.getId());
        request.setAttribute("resumeList", resumeList);
        System.out.println(resumeList.size());

        ArrayList<ResumeResponse.DetailSkillDTO> resumeSkillList = new ArrayList<>();
        for (int i = 0; i < resumeList.size(); i++) {
            List<String> skills = skillRepository.findByResumeId(resumeList.get(i).getId());
            System.out.println(skills);
            ResumeResponse.DetailDTO resume = resumeList.get(i);
            System.out.println(resume);

            resumeSkillList.add(new ResumeResponse.DetailSkillDTO(resume, skills));
            System.out.println(resumeSkillList.get(i));
        }
        request.setAttribute("resumeSkillList", resumeSkillList);

        return "person/resumes";
    }

    @GetMapping("/person/resume/{id}/detail")
    public String personResumeDetailForm(@PathVariable int id, HttpServletRequest request) {
        System.out.println("id: " + id);

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/person/loginForm";
        }

//        Resume resume = resumeRepository.findById(id);
//        List<String> skills = skillRepository.findByResumeId(id);
//        ResumeResponse.DetailDTO detailDTO = new ResumeResponse.DetailDTO(new Resume());
//        detailDTO.setSkill(skills);

//        List<ResumeResponse.DetailDTO> resumeList = resumeRepository.findResume(sessionUser.getId());
//        request.setAttribute("resumeList", resumeList);
//
//        ArrayList<ResumeResponse.DetailSkillDTO> resumeSkillList = new ArrayList<>();
//        for (int i = 0; i < resumeList.size(); i++) {
//            List<String> skills = skillRepository.findByResumeId(resumeList.get(i).getId());
//            System.out.println(skills);
//            ResumeResponse.DetailDTO resume = resumeList.get(i);
//            System.out.println(resume);
//
//            resumeSkillList.add(new ResumeResponse.DetailSkillDTO(resume, skills));
//            System.out.println(resumeSkillList.get(i));
//        }
//        request.setAttribute("resumeSkillList", resumeSkillList);

        ResumeResponse.DetailDTO responseDTO = resumeRepository.findById(id); //스킬빼고 담고온거
        List<String> skills = skillRepository.findByResumeId(responseDTO.getId());
        ResumeResponse.DetailSkillDTO resumeSkill= new ResumeResponse.DetailSkillDTO(responseDTO, skills);
        System.out.println(sessionUser);
        request.setAttribute("resume", resumeSkill);
        System.out.println(responseDTO);

        return "person/resumeDetail";
    }

    @GetMapping("/person/resume/saveForm")
    public String personSaveResumeForm(ResumeRequest.SaveDTO requestDTO, HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/person/loginForm";
        }
        System.out.println(sessionUser);
//        Resume resume = resumeRepository.findById(id);
//        List<String> skills = skillRepository.findByResumeId(id);
//        ResumeResponse.DetailDTO  detailDTO= new ResumeResponse.DetailDTO(new Resume());
//        detailDTO.setSkill(skills);
//
        request.setAttribute("person", sessionUser);
        request.setAttribute("resume", requestDTO);
        return "person/saveResumeForm";
    }

    @PostMapping("/person/resume/save")
    public String personSaveResume(ResumeRequest.SaveDTO requestDTO, HttpServletRequest request, @RequestParam("skills") List<String> skills) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/person/loginForm";
        }

        System.out.println(requestDTO);

        List<SkillRequest.SaveDTO> skillDTOs = new ArrayList<>(); // 스킬을 저장할 DTO 리스트 생성

        // 각 스킬을 SkillRequest.SaveDTO 형태로 변환하여 리스트에 추가
        for (String skill : skills) {
            SkillRequest.SaveDTO skillDTO = new SkillRequest.SaveDTO();
            skillDTO.setSkill(skill);
            skillDTO.setResumeId(requestDTO.getId());
            skillDTOs.add(skillDTO);
        }

        // 변환된 스킬 DTO 리스트를 사용하여 저장
        int resumeId = resumeRepository.save(requestDTO);
        skillRepository.saveSkillsFromResume(skillDTOs, resumeId);
        request.setAttribute("resume", requestDTO);
        request.setAttribute("skills", skills);
        System.out.println(skills);

        return "redirect:/person/resume";
    }

    @GetMapping("/person/resume/{id}/detail/updateForm")
    public String personUpdateResumeForm(@PathVariable int id, HttpServletRequest request) {
        ResumeResponse.DetailDTO detailDTO= resumeRepository.findById(id);
        request.setAttribute("resume", detailDTO);
        return "person/updateResumeForm";
    }

    @PostMapping("/person/resume/{id}/detail/update")
    public String personUpdateResume(@PathVariable int id, ResumeRequest.UpdateDTO requestDTO, HttpServletRequest request, @RequestParam("skills") List<String> skills) {
        System.out.println("🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗");
        System.out.println(requestDTO);

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/person/loginForm";
        }
        System.out.println(1);
        List<SkillRequest.SaveDTO> skillDTOs = new ArrayList<>(); // 스킬을 저장할 DTO 리스트 생성
        System.out.println(2);
        skillRepository.resetSkill(id);
        for (String skill : skills) {
            SkillRequest.SaveDTO skillDTO = new SkillRequest.SaveDTO();
            skillDTO.setSkill(skill);
            skillDTO.setResumeId(requestDTO.getId());
            skillDTOs.add(skillDTO);
        }

//        ResumeRequest.UpdateDTO updatedResume = resumeRepository.save(requestDTO); // 이력서 업데이트
        resumeRepository.update(id, requestDTO);
        System.out.println(requestDTO);
//        skillRepository.saveSkillsFromResume(skillDTOs);
        System.out.println(3);
//        ResumeResponse.DetailDTO UpdateDTO = resumeRepository.findById(id);
//        resumeRepository.findById(id);

        // 업데이트된 이력서 정보와 스킬 정보를 반환
        request.setAttribute("resume", requestDTO);
        request.setAttribute("skills", skills);
        return "redirect:/person/resume/"+id+"/detail";
    }

    @PostMapping("/person/resume/{id}/detail/delete")
    public String personDeletePost(@PathVariable int id, HttpServletRequest request) {
        resumeRepository.delete(id);
        return "redirect:/person/resume";
    }

//    //메인 구직 공고
//    @GetMapping("/resume")
//    public String resumeForm() {return "person/main";}
//
//    @GetMapping("/resume/detail/{id}")
//    public String resumeDetailForm(@PathVariable int id) {
//        return "person/resumeDetail";
//    }
//
//    //맞춤 공고 - 기업용
//    @GetMapping("/matching/resume")
//    public String matchingResumeForm() {return "company/matching";}
//
//    @GetMapping("/matching/resume/detail/{id}")
//    public String matchingResumeDetailForm(@PathVariable int id) {
//        return "person/resumeDetail";
//    }
}