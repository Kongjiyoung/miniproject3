package com.many.miniproject1.resume;

import com.many.miniproject1._core.common.ProfileImageService;
import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;

import com.many.miniproject1.skill.SkillResponse;

import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;
    private final ResumeQueryRepository resumeQueryRepository;
    private final SkillJPARepository skillJPARepository;

    private final UserJPARepository userJPARepository;


    @Transactional
    public Resume update(int resumeId, ResumeRequest.UpdateDTO requestDTO) {

        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        resume.setTitle(requestDTO.getTitle());
        resume.setPortfolio(requestDTO.getPortfolio());
        resume.setIntroduce(requestDTO.getIntroduce());
        resume.setCareer(requestDTO.getCareer());
        resume.setSimpleIntroduce(requestDTO.getSimpleIntroduce());
        resume.setProfile(ProfileImageService.saveProfile(requestDTO.getProfile()));

        List<Skill> skills=skillJPARepository.findSkillsByResumeId(resume.getId());
        for (Skill skill:skills) {
            skillJPARepository.deleteSkillsByResumeId(resume.getId());
        }
        List<Skill> skills1 = new ArrayList<>();
        for (String skillName : requestDTO.getSkills()) {
            SkillResponse.SaveDTO skill = new SkillResponse.SaveDTO();
            skill.setResume(resume);
            skill.setSkill(skillName);
            skills1.add(skill.toEntity());
        }

        List<Skill> skillList = skillJPARepository.saveAll(skills1);
        return resume;
    }

    public Resume findByResume(int id){
        Resume resume = resumeJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        return resume;
    }

    @Transactional
    public Resume save(ResumeRequest.SaveDTO requestDTO, User sessionUser) {

        Resume resume = resumeJPARepository.save(requestDTO.toEntity(sessionUser));

        List<Skill> skills = new ArrayList<>();
        for (String skillName : requestDTO.getSkills()) {
            SkillResponse.SaveDTO skill = new SkillResponse.SaveDTO();
            skill.setResume(resume);
            skill.setSkill(skillName);
            skills.add(skill.toEntity());
        }

        List<Skill> skillList = skillJPARepository.saveAll(skills);


        return resume;
    }


    public Resume getResumeDetail(ResumeResponse.ResumeDetailDTO respDTO) {
        return resumeJPARepository.findByIdJoinSkillAndUser(respDTO.getId());
    }


    public List<Resume> findResumeList(Integer userId) {
        return resumeJPARepository.findByUserId(userId);
    }

    public void deleteResume(Integer id){

        resumeJPARepository.deleteById(id);

    }
}
