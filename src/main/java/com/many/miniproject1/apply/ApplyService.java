package com.many.miniproject1.apply;


import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.skill.SkillJPARepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyJPARepository applyJPARepository;
    private final ApplyQueryRepository applyQueryRepository;
    private final SkillJPARepository skillJPARepository;

    public Apply getPostDetail(Integer userId, Integer postId){
        Apply apply = applyJPARepository.findByPostIdJoinPostAndSkillAndUser(postId,userId).orElseThrow(() -> new Exception404("없음"));
        return apply;
    }
}
