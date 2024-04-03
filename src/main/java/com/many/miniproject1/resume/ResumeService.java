package com.many.miniproject1.resume;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1._core.errors.exception.Exception403;
import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.scrap.Scrap;
import com.many.miniproject1.scrap.ScrapJPARepository;
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
    private final SkillJPARepository skillJPARepository;
    private final ApplyJPARepository applyJPARepository;
    private final OfferJPARepository offerJPARepository;
    private final ScrapJPARepository scrapJPARepository;

    private final UserJPARepository userJPARepository;

    @Transactional
    public ResumeResponse.ResumeSaveDTO resumeSave (ResumeRequest.ResumeSaveDTO reqDTO, User sessionUser){
//        Skill List<skill>
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


    public Resume resumeUpdate(int id, User user) {

        return null;
    }

    @Transactional
    public Resume update(int resumeId, ResumeRequest.UpdateDTO requestDTO) {

        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        resume.setTitle(requestDTO.getTitle());
        resume.setPortfolio(requestDTO.getPortfolio());
        resume.setIntroduce(requestDTO.getIntroduce());
        resume.setCareer(requestDTO.getCareer());
        resume.setSimpleIntroduce(requestDTO.getSimpleIntroduce());
        resume.setProfile(ProfileImageSaveUtil.save(requestDTO.getProfile()));

        List<Skill> skills = skillJPARepository.findSkillsByResumeId(resume.getId());
        for (Skill skill : skills) {
            skillJPARepository.deleteSkillsByResumeId(resume.getId());
        }
        List<Skill> skills1 = new ArrayList<>();
        for (String skillName : requestDTO.getSkills()) {
            SkillResponse.SaveResumeDTO skill = new SkillResponse.SaveResumeDTO();
            skill.setResume(resume);
            skill.setSkill(skillName);
            skills1.add(skill.toEntity());
        }

        List<Skill> skillList = skillJPARepository.saveAll(skills1);
        return resume;
    }

    public Resume findByResume(int id) {
        Resume resume = resumeJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        return resume;
    }

    @Transactional
    public Resume save(ResumeRequest.SaveDTO requestDTO, User sessionUser) {

        Resume resume = resumeJPARepository.save(requestDTO.toEntity(sessionUser));

        List<Skill> skills = new ArrayList<>();
        for (String skillName : requestDTO.getSkills()) {
            SkillResponse.SaveResumeDTO skill = new SkillResponse.SaveResumeDTO();
            skill.setResume(resume);
            skill.setSkill(skillName);
            skills.add(skill.toEntity());
        }

        List<Skill> skillList = skillJPARepository.saveAll(skills);


        return resume;
    }

    public Resume getResumeDetail(int resumeId) {
        return resumeJPARepository.findByIdJoinSkillAndUser(resumeId);
    }

    public ResumeResponse.resumeDetailDTO getResumeDetail(int resumeId, User sessionUser) {
        Resume resume = resumeJPARepository.findByIdJoinUser(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        return new ResumeResponse.resumeDetailDTO(resume, sessionUser);
    }

    public Resume getResumeSkill(ResumeResponse.DetailDTO respDTO) {
        ResumeRequest.ResumeDTO resumeDTO = new ResumeRequest.ResumeDTO();
        List<Resume> resumeList = findResumeList(resumeDTO.getId());
        ArrayList<ResumeResponse.DetailSkillDTO> resumeSkillList = new ArrayList<>();
        for (int i = 0; i < resumeList.size(); i++) {
            List<Skill> skills = skillJPARepository.findSkillsByResumeId(resumeList.get(i).getId());
            Resume resume = resumeList.get(i);
            resumeSkillList.add(new ResumeResponse.DetailSkillDTO(resume, skills));
        }
        return resumeJPARepository.findByIdJoinSkill(respDTO.getId());
    }

    public List<Resume> findResumeList(Integer userId) {

        return resumeJPARepository.findByUserIdJoinSkillAndUser(userId);
    }

    public List<ResumeResponse.resumeListDTO> getResumeList(int userId) {
        List<Resume> resumeList = resumeJPARepository.findAllResume(userId);
        return resumeList.stream().map(resume -> new ResumeResponse.resumeListDTO(resume)).toList();
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


    public List<Resume> getResumeFindBySessionUserId(Integer sessionUserId) {
        List<Resume> resumeList = resumeJPARepository.findBySessionUserId(sessionUserId);
        return resumeList;
    }

}
