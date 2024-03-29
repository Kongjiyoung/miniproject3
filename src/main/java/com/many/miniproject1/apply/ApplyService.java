package com.many.miniproject1.apply;


import com.many.miniproject1.post.Post;
import com.many.miniproject1._core.errors.exception.Exception404;


import jakarta.transaction.Transactional;


import java.util.Optional;


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


    @Transactional
    public Apply isPassResume(ApplyRequest.UpdateIsPass reqDTO) {
        // 1. 이력서 찾기
        Apply apply = applyJPARepository.findById(reqDTO.getId())
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        System.out.println(apply);

        apply.setIsPass(reqDTO.getIsPass());
        return apply;
    }


  
    public List<Apply> companyResumes(Integer userId) {

        applyJPARepository.findByUserIdJoinPost(userId);

        System.out.println(applyJPARepository.findByUserIdJoinPost(userId));

        return applyJPARepository.findByUserIdJoinPost(userId);
    }




    public Apply companyResumeDetail(int id, int userId){
        Apply apply = applyJPARepository.findByResumeIdJoinSkillAndCompany(id, userId);
        return apply;
    }
    public Apply findById(int id) {
        Apply apply = applyJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        return apply;
    }


    @Transactional
    public void deleteApplyPost(int id) {
        applyJPARepository.deleteApplyPostById(id);
    }

    // 개인이 지원한 이력서 목록 YSH
    public List<Apply> getApplyList (Integer userId){
        return applyJPARepository.findAllAppliesWithPostsAndSkills(userId);
}

    public Apply getPostDetail(Integer userId, Integer postId){
        Apply apply = applyJPARepository.findByPostIdJoinPostAndSkillAndUser(postId,userId).orElseThrow(() -> new Exception404("없음"));
        return apply;
    }

}
