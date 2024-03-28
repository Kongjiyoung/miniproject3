package com.many.miniproject1.apply;


import com.many.miniproject1.skill.SkillJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyJPARepository applyJPARepository;
    private final ApplyQueryRepository applyQueryRepository;
    private final SkillJPARepository skillJPARepository;

    public List<Apply> companyResumes(Integer userId) {

        // 목적: 개인이 지원한 이력서를 들고오기!!!(목록보기)
        // 회사에 도착한 이력서를 가져오려면 어떻게 해야 할까요?
        // 1. 회사 아이디가 필요!!
        // 2. 해당 회사 아이디가 매칭된 이력서를 들고 와야 한다!(applied resume)

        //회사 아이디로 해당 회사에 속한 지원한 이력서들을 조회
//        List<Apply> appliedResumes = applyJPARepository.findByPostUserId(sessionUser.getId());

        // 지원한 이력서 정보를 담을 리스트
//        List<ApplyResponse.ApplyResumeDTO> resumeList =


//
//            resumeSkillList.add(new ApplyResponse.ResumeIsPassDTO(resume, skills));
        applyJPARepository.findByUserIdJoinPost(userId);

        System.out.println(applyJPARepository.findByUserIdJoinPost(userId));

        return applyJPARepository.findByUserIdJoinPost(userId);
    }


}
