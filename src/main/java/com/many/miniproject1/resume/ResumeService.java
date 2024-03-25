package com.many.miniproject1.resume;

import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.skill.Skill;
import com.many.miniproject1.skill.SkillJPARepository;
import com.many.miniproject1.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

    public List<Resume> findResumesByUserId(Integer userId) {
        return resumeJPARepository.findByUserId(userId);
    }
}