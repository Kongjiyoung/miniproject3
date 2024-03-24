package com.many.miniproject1.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;
    private final ResumeQueryRepository resumeQueryRepository;

    @Transactional
    public Resume save(ResumeRequest.SaveDTO requestDTO) {

        return resume;
    }
}
