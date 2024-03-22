package com.many.miniproject1.backup.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SkillService {
    private final SkillJPARepository skillJPARepository;
    private final SkillQueryRepository skillQueryRepository;
}
