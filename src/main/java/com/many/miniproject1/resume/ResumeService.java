package com.many.miniproject1.resume;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import com.many.miniproject1._core.errors.exception.Exception403;
import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.scrap.ScrapJPARepository;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import com.many.miniproject1.skill.SkillRequest;
import com.many.miniproject1.user.SessionUser;
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

    //이력서 목록보기
    public List<ResumeResponse.ResumeListDTO> getResumeList(Integer userId) {
        List<Resume> resumeList = resumeJPARepository.findAllResume(userId);
        return resumeList.stream().map(ResumeResponse.ResumeListDTO::new).toList();
    }

    //이력서 상세보기
    public ResumeResponse.ResumeDetailDTO getResumeDetail(Integer resumeId, Integer sessionUserId) {
        Resume resume = resumeJPARepository.findByIdJoinUser(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        if (sessionUserId != resume.getId()) {
            throw new Exception403("이력서를 볼 권한이 없습니다");
        }

        return new ResumeResponse.ResumeDetailDTO(resume);
    }

    //이력서 저장
    @Transactional
    public ResumeResponse.ResumeSaveDTO resumeSave (ResumeRequest.ResumeSaveDTO reqDTO, SessionUser sessionUser){
        User user = userJPARepository.findById(sessionUser.getId()).orElseThrow(() -> new Exception401("로그인해주세요요."));

        //이력서 저장
        Resume resume = resumeJPARepository.save(reqDTO.toEntity(user));

        //스킬 저장
        List<Skill> skills = new ArrayList<>();
        for (String skillName : reqDTO.getSkills()) {
            SkillRequest.SaveResumeDTO skill = new SkillRequest.SaveResumeDTO();
            skills.add(skill.toEntity(skillName, resume));
        }
        List<Skill> skillList = skillJPARepository.saveAll(skills);

        return new ResumeResponse.ResumeSaveDTO(resume, skillList);
    }

    //이력서 업데이트
    @Transactional
    public ResumeResponse.UpdateDTO resumeUpdate(Integer resumeId, ResumeRequest.UpdateDTO reqDTO) {
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        //이력서 업데이트
        resume.updateResume(reqDTO);
        resume.setProfile(ProfileImageSaveUtil.convertToBase64(reqDTO.getProfile(),reqDTO.getProfileName()));

        //스킬 전체 삭제
        List<Skill> beforeSkill = skillJPARepository.findSkillsByPostId(resume.getId());
        for (Skill skill : beforeSkill) {
            skillJPARepository.deleteSkillsByPostId(resume.getId());
        }

        //스킬 업데이트
        List<Skill> skills = new ArrayList<>();
        for (String skillName : reqDTO.getSkills()) {
            SkillRequest.SaveResumeDTO skill = new SkillRequest.SaveResumeDTO();
            skills.add(skill.toEntity(skillName, resume));
        }
        List<Skill> skillList = skillJPARepository.saveAll(skills);

        return new ResumeResponse.UpdateDTO(resume, skillList);
    }

    //이력서 삭제
    @Transactional
    public void deleteResumeId(Integer resumeId, Integer sessionUserId) {
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
