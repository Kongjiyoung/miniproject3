package com.many.miniproject1.post;

import com.many.miniproject1.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostJPARepository postJPARepository;
    private final PostQueryRepository postQueryRepository;

    @Transactional
    public void save(PostRequest.PostSaveDTO reqDTO, User sessionUser){
        postJPARepository.save(reqDTO.toEntity(sessionUser));
    }
}
