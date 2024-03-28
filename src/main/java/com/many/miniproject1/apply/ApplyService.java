package com.many.miniproject1.apply;


import com.many.miniproject1.post.Post;
import com.many.miniproject1._core.errors.exception.Exception404;
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

    // 개인이 지원한 이력서 목록 YSH
    public List<Apply> getApplyList (Integer userId){
        return applyJPARepository.findAllAppliesWithPostsAndSkills(userId);

    public Apply getPostDetail(Integer userId, Integer postId){
        Apply apply = applyJPARepository.findByPostIdJoinPostAndSkillAndUser(postId,userId).orElseThrow(() -> new Exception404("없음"));
        return apply;
    }
}
