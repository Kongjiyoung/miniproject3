package com.many.miniproject1.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostJPARepository postJPARepository;
    private final PostQueryRepository postQueryRepository;
    public List<Post> getResumeList(Integer userId){
        return postJPARepository.findByUserIdJoinSkillAndUser(userId);
    }
}
