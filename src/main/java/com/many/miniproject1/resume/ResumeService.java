package com.many.miniproject1.resume;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1._core.errors.exception.Exception403;
import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.scrap.ScrapJPARepository;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import com.many.miniproject1.skill.SkillResponse;
import com.many.miniproject1.user.SessionUser;
import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;
    private final SkillJPARepository skillJPARepository;
    private final ApplyJPARepository applyJPARepository;
    private final OfferJPARepository offerJPARepository;
    private final ScrapJPARepository scrapJPARepository;
    private final UserJPARepository userJPARepository;


    public List<ResumeResponse.ResumeListDTO> getResumeList(int userId) {
        List<Resume> resumeList = resumeJPARepository.findAllResume(userId);
        return resumeList.stream().map(resume -> new ResumeResponse.ResumeListDTO(resume)).toList();
    }

    public ResumeResponse.ResumeDetailDTO getResumeDetail(int resumeId, int sessionUserId) {
        Resume resume = resumeJPARepository.findByIdJoinUser(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        if (sessionUserId != resume.getId()) {
            throw new Exception403("이력서를 볼 권한이 없습니다");
        }
        return new ResumeResponse.ResumeDetailDTO(resume);
    }

    @Transactional
    public ResumeResponse.ResumeSaveDTO resumeSave (ResumeRequest.ResumeSaveDTO reqDTO, SessionUser sessionUser){
        User user = userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new Exception401("로그인"));
        Resume resume = resumeJPARepository.save(reqDTO.toEntity(user));
        List<Skill> skills = new ArrayList<>();
        for (String skillName : reqDTO.getSkills()) {
            SkillResponse.SaveResumeDTO skill = new SkillResponse.SaveResumeDTO();
            skill.setSkill(skillName);
            skill.setResume(resume);
            skills.add(skill.toEntity());
        }
        List<Skill> skillList = skillJPARepository.saveAll(skills);
        return new ResumeResponse.ResumeSaveDTO(resume, skillList);
    }

    @Transactional
    public ResumeResponse.UpdateDTO resumeUpdate(int resumeId, ResumeRequest.UpdateDTO reqDTO) {
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        if (reqDTO.getProfile() != null) {
            resume.setProfile(ProfileImageSaveUtil.convertToBase64(reqDTO.getProfile(), reqDTO.getProfileName()));
        }

        if (reqDTO.getProfileName() != null) {
            resume.setProfileName(reqDTO.getProfileName());
        }

        if (reqDTO.getTitle() != null) {
            resume.setTitle(reqDTO.getTitle());
        }
        if (reqDTO.getPortfolio() != null) {
            resume.setPortfolio(reqDTO.getPortfolio());
        }
        if (reqDTO.getIntroduce() != null) {
            resume.setIntroduce(reqDTO.getIntroduce());
        }
        if (reqDTO.getCareer() != null) {
            resume.setCareer(reqDTO.getCareer());
        }
        if (reqDTO.getSimpleIntroduce() != null) {
            resume.setSimpleIntroduce(reqDTO.getSimpleIntroduce());
        }


        // 4. 스킬 업데이트
        List<Skill> beforeSkill = skillJPARepository.findSkillsByPostId(resume.getId());
        for (Skill skill : beforeSkill) {
            skillJPARepository.deleteSkillsByPostId(resume.getId());
        }


        List<Skill> skills = new ArrayList<>();
        for (String skillName : reqDTO.getSkills()) {
            SkillResponse.SaveResumeDTO skill = new SkillResponse.SaveResumeDTO();
            skill.setSkill(skillName);
            skill.setResume(resume);
            skills.add(skill.toEntity());
        }
        List<Skill> skillList = skillJPARepository.saveAll(skills);
        return new ResumeResponse.UpdateDTO(resume, skillList);
    }

    @Transactional
    public void deleteResumeId(Integer resumeId, int sessionUserId) {
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        if(sessionUserId != resume.getUser().getId()){
            throw new Exception403("이력서를 삭제할 권한이 없습니다");
        }
        resumeJPARepository.deleteById(resumeId);
        applyJPARepository.deleteByResumeId(resumeId);
        resumeJPARepository.deleteById(resumeId);
        offerJPARepository.deleteByResumeId(resumeId);
        scrapJPARepository.deleteByResumeId(resumeId);
        skillJPARepository.deleteSkillsByResumeId(resumeId);
    }
}
