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
    public List<ResumeResponse.DetailDTO> findByResumeDetail (int resumeId, User sessionUser){

        List<ResumeResponse.DetailDTO> resumeList = (List<ResumeResponse.DetailDTO>) resumeJPARepository.findByIdJoinUser(resumeId)
                .orElseThrow(()-> new Exception404("게시글을 찾을 수 없습니다."));
        ArrayList<ResumeResponse.DetailSkillDTO> resumeSkillList = new ArrayList<>();
        for (int i = 0; i <resumeList.size(); i++){
            Optional<Skill> skills = skillJPARepository.findById(resumeList.get(i).getId());
            ResumeResponse.DetailDTO resume = resumeList.get(i);
            resumeSkillList.add(new ResumeResponse.DetailSkillDTO(resume, skills));
        }

        Resume resume = new Resume();
        boolean isResumeOwner = false;
        if (sessionUser != null){
            if (sessionUser.getId() == resume.getUser().getId()){
                isResumeOwner = true;
            }
        }
        resume.setResumeOwner(isResumeOwner);


        return resumeList;
    }
}