package com.many.miniproject1.resume;

import com.many.miniproject1.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;
    private final ResumeQueryRepository resumeQueryRepository;

    @Transactional
    public Resume save(ResumeRequest.SaveDTO requestDTO, User sessionUser) {

        Resume resume=resumeJPARepository.save(requestDTO.toEntity(requestDTO.toEntity(requestDTO, sessionUser)));
        return resume;
    }
}