package com.many.miniproject1.backup.skill;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SkillQueryRepository {
    private final EntityManager em;
}
