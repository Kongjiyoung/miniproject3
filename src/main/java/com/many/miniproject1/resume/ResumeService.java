package com.many.miniproject1.resume;

import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import com.many.miniproject1.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;
    private final ResumeQueryRepository resumeQueryRepository;
    private final SkillJPARepository skillJPARepository;
    @Transactional
    public Resume save(ResumeRequest.SaveDTO requestDTO, User sessionUser) {

        Resume resume=resumeJPARepository.save(requestDTO.toEntity());
        //Skill skill = skillJPARepository.saveAll()
        return resume;
    }
//    public List<ResumeResponse.DetailDTO> findByResumeDetail (int resumeId, User sessionUser){
//        Resume resume = resumeJPARepository.findById(resumeId)
//                .orElseThrow(()-> new Exception404("게시글을 찾을 수 없습니다."));
//        List<ResumeResponse.DetailDTO> resumeList = resumeJPARepository.findByUserIdJoinUser(resume.getUser().getId());
//        List<ResumeResponse.DetailSkillDTO> resumeSkillList = new ArrayList<>();
//        List<Skill> skills = skillJPARepository.findSkillsByResumeId(resumeId); // 가정한 메서드
//        List<String> skillNames = skills.stream().map(Skill::getName).collect(Collectors.toList());
//
//        for (ResumeResponse.DetailDTO resumeDTO : resumeList) {
//            ResumeResponse.DetailSkillDTO resumeSkillDTO = new ResumeResponse.DetailSkillDTO(resumeDTO, skillNames);
//            resumeSkillList.add(resumeSkillDTO);
//        }
//
//        boolean isResumeOwner = (sessionUser != null && sessionUser.getId() == resume.getUser().getId());
//        resume.setResumeOwner(isResumeOwner);
//
//        return resumeList;
//    }
}