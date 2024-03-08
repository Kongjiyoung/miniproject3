package com.many.miniproject1.resume;

import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillRepository;
import com.many.miniproject1.skill.SkillRequest;
import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserFileService;
import com.many.miniproject1.user.UserRepository;
import com.many.miniproject1.user.UserRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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
public class ResumeController {
    private final ResumeRepository resumeRepository;
    private final SkillRepository skillRepository;
    private final HttpSession session;
    private final UserFileService userFileService;
    private final UserRepository userRepository;

    //개인 이력서 관리
    @GetMapping("/person/resume")
    public String personResumeForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ResumeResponse.DetailDTO> resumeList = resumeRepository.findresume(sessionUser.getId());
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
        ResumeResponse.DetailSkillDTO resumeSkill = new ResumeResponse.DetailSkillDTO(responseDTO, skills);
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

        MultipartFile profileImage = requestDTO.getProfile();
        String profilePath = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                String absolutePath = userFileService.saveFile(profileImage);
                String filename = Paths.get(absolutePath).getFileName().toString();
                profilePath = filename;
                System.out.println("Saved file name: " + profilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        requestDTO.setProfilePath(profilePath);
        // 이력서 저장 및 저장된 이력서 ID 획득
        int resumeId = resumeRepository.save(requestDTO);

        // 스킬 저장
        List<ResumeResponse.skillDTO> skillDTOList=new ArrayList<>();
        for(String skill:skills){
            ResumeResponse.skillDTO skillDTO=new ResumeResponse.skillDTO();
            skillDTO.setSkill(skill);
            skillDTO.setResumeId(resumeId);
            skillDTOList.add(skillDTO);
        }

        // 스킬 저장
        skillRepository.saveSkillsFromResume(skillDTOList);

        request.setAttribute("resume", requestDTO);
        request.setAttribute("skills", skills);
        System.out.println(skills);

        return "redirect:/person/resume";
    }

    @GetMapping("/person/resume/detail/{id}/updateForm")
    public String personUpdateResumeForm(@PathVariable int id, HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/person/loginForm";
        }
        ResumeResponse.DetailDTO detailDTO = resumeRepository.findById(id);
//        ResumeResponse.DetailDTO detailDTO= new ResumeResponse.DetailDTO(new Resume());

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
        // 이미지 파일 처리
        MultipartFile profileImage = requestDTO.getProfile();
        String profilePath = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                String absolutePath = userFileService.saveFile(profileImage);
                String filename = Paths.get(absolutePath).getFileName().toString();
                profilePath = filename;
                System.out.println("Saved file path: " + profilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 이미지를 선택하지 않은 경우, 기존 이미지 경로를 가져옴
            ResumeResponse.DetailDTO existingResume = resumeRepository.findById(id);
            profilePath = existingResume.getProfile();
        }
        requestDTO.setProfilePath(profilePath);

        resumeRepository.skilldelete(id);
        requestDTO.setProfilePath(profilePath);
        // 이력서 저장 및 저장된 이력서 ID 획득
        int resumeId = resumeRepository.update(id, requestDTO);

        // 스킬 저장
        List<ResumeResponse.skillDTO> skillDTOList=new ArrayList<>();
        for(String skill:skills){
            ResumeResponse.skillDTO skillDTO=new ResumeResponse.skillDTO();
            skillDTO.setSkill(skill);
            skillDTO.setResumeId(resumeId);
            skillDTOList.add(skillDTO);
        }

        // 스킬 저장
        skillRepository.saveSkillsFromResume(skillDTOList);

//        ResumeRequest.UpdateDTO updatedResume = resumeRepository.save(requestDTO); // 이력서 업데이트

//        ResumeResponse.DetailDTO UpdateDTO = resumeRepository.findById(id);
//        resumeRepository.findById(id);

        // 업데이트된 이력서 정보와 스킬 정보를 반환
        request.setAttribute("resume", requestDTO);
        request.setAttribute("skills", skills);
        return "redirect:/person/resume/" + id + "/detail";

    }

    @PostMapping("/person/resume/detail/{id}/delete")
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